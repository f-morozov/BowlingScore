package ru.hh.school;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BowlingGame {

    public BowlingGame(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void runGame() {
        scanner = new Scanner(in);
        int numPlayers = 0;
        while(numPlayers <= 0)
            numPlayers = getInt("Please enter number of players: ");
        initScores(numPlayers);

        for(int frame = 0; frame < BowlingScore.numFrames; frame++) {
            out.printf("\nStarting frame %d\n", frame + 1);
            for (int i = 0; i < numPlayers; i++)
                processPlayer(i, frame);
            printScores();
        }
    }

    private void initScores(int numPlayers) {
        scores = new ArrayList<>(numPlayers);
        for(int i = 0; i < numPlayers; i++)
            scores.add(new BowlingScore());
    }

    private void printScores() {
        out.print("Resulting scores: ");
        for(BowlingScore score : scores)
            out.printf("%d ", score.getScore());
    }

    private void processPlayer(int player, int frame) {
        while(scores.get(player).getFrameIndex() == frame) {
            int score = getInt(String.format("Please enter player %d score: ", player + 1));
            if (!scores.get(player).addRoll(score))
                out.print("Wrong points count. ");
        }
    }

    private int getInt(String prompt) {
        while(true) {
            out.print(prompt);
            String token = scanner.nextLine();
            try {
                return Integer.parseInt(token);
            } catch(NumberFormatException exception) {
                out.print("Unrecognized number format. ");
            }
        }
    }

    private InputStream in;
    private Scanner scanner;
    private PrintStream out;
    ArrayList<BowlingScore> scores;
}
