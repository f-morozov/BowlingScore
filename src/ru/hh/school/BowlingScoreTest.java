package ru.hh.school;

import junit.framework.TestCase;

public class BowlingScoreTest extends TestCase {

    public void testRollLimits() throws Exception {
        BowlingScore score = new BowlingScore();
        assertFalse(score.addRoll(11));
        assertFalse(score.addRoll(-1));
        assertTrue(score.addRoll(0));
        assertTrue(score.addRoll(10));
        assertTrue(score.addRoll(5));
    }

    public void testFrameScoreLimit() throws Exception {
        BowlingScore score = new BowlingScore();
        assertTrue(score.addRoll(5));
        assertFalse(score.addRoll(6));
        assertTrue(score.addRoll(4));
    }

    public void testSimpleFrame() throws Exception {
        BowlingScore score = new BowlingScore();
        score.addRoll(4);
        score.addRoll(5);
        assertEquals(score.getScore(), 9);
        assertEquals(score.getFrameIndex(), 1);
    }

    public void testStrike() throws Exception {
        BowlingScore score = new BowlingScore();
        score.addRoll(10);
        assertEquals(score.getFrameIndex(), 1);
        score.addRoll(3);
        score.addRoll(6);
        assertEquals(score.getFrameIndex(), 2);
        score.addRoll(2);
        assertEquals(score.getScore(), 30);
    }

    public void testDoubleStrike() throws Exception {
        BowlingScore score = new BowlingScore();
        score.addRoll(10);
        score.addRoll(10);
        score.addRoll(3);
        score.addRoll(5);
        score.addRoll(1);
        assertEquals(score.getScore(), 50);
    }

    public void testSpare() throws Exception {
        BowlingScore score = new BowlingScore();
        score.addRoll(9);
        score.addRoll(1);
        score.addRoll(3);
        score.addRoll(4);
        assertEquals(score.getScore(), 20);
        score.addRoll(0);
        score.addRoll(10);
        score.addRoll(3);
        score.addRoll(4);
        assertEquals(score.getScore(), 40);
    }

    public void testFrameCount() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames; i++) {
            score.addRoll(1);
            score.addRoll(4);
        }
        assertEquals(score.getFrameIndex(), BowlingScore.numFrames);
        assertEquals(score.addRoll(4), false);
        assertEquals(score.getScore(), 50);
    }

    public void testLastFrameStrike() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames - 1; i++) {
            score.addRoll(1);
            score.addRoll(4);
        }
        score.addRoll(10);
        assertTrue(score.addRoll(2));
        assertTrue(score.addRoll(3));
        assertEquals(score.getFrameIndex(), BowlingScore.numFrames);
        assertEquals(score.getScore(), 60);
    }

    public void testLastFrameDoubleStrike() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames - 1; i++) {
            score.addRoll(1);
            score.addRoll(4);
        }
        score.addRoll(10);
        assertTrue(score.addRoll(10));
        assertTrue(score.addRoll(5));
        assertEquals(score.getFrameIndex(), BowlingScore.numFrames);
        assertEquals(score.getScore(), 70);
    }

    public void testLastFrameLimits() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames - 1; i++) {
            score.addRoll(1);
            score.addRoll(4);
        }
        score.addRoll(10);
        assertTrue(score.addRoll(7));
        assertFalse(score.addRoll(4));
        assertTrue(score.addRoll(2));
        assertEquals(score.getFrameIndex(), BowlingScore.numFrames);
        assertEquals(score.getScore(), 64);
    }

    public void testLastFrameSpare() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames - 1; i++) {
            assertEquals(score.addRoll(1), true);
            assertEquals(score.addRoll(4), true);
        }
        score.addRoll(4);
        score.addRoll(6);
        assertEquals(score.addRoll(5), true);
        assertEquals(score.getFrameIndex(), BowlingScore.numFrames);
        assertEquals(score.getScore(), 60);
    }

    public void testMaxScore() throws Exception {
        BowlingScore score = new BowlingScore();
        for(int i = 0; i < BowlingScore.numFrames; i++) {
            score.addRoll(10);
        }
        score.addRoll(10);
        score.addRoll(10);
        assertEquals(score.getScore(), 300);
    }

    public void testSampleGame() throws Exception {
        BowlingScore score = new BowlingScore();
        int[] rolls = {3, 5, 10, 4, 6, 7, 1, 0, 10, 2, 4, 10, 0, 1, 3, 5, 8, 2, 10};
        for(int roll : rolls)
            assertTrue(score.addRoll(roll));
        assertEquals(score.getFrameIndex(), 10);
        assertEquals(score.getScore(), 111);
    }
}