package ru.hh.school;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class BowlingGameTest {

    @Test
    public void testGame() {
        String in = "2 5 4 3 5 10 3 7 2 4 8 0 9 1 2 6 3 4 10 5 2 3 1 7 1 8 2 10 5 3 1 6 7 2 10 3 5 6 4 7";
        in = in.replace(' ', '\n');
        // Player 1: 5 4 | 10  | 2 4 | 9 1 | 3 4 | 5 2 | 7 1 | 10  | 1 6 | 10 3 5 = 108
        // Player 2: 3 5 | 3 7 | 8 0 | 2 6 | 10  | 3 1 | 8 2 | 5 3 | 7 2 | 6 4 7  = 109

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BowlingGame game = new BowlingGame(new ByteArrayInputStream(in.getBytes()), new PrintStream(out));
        game.runGame();
        assertTrue(out.toString().endsWith("108 109 "));
    }
}