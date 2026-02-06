/*
 *
 * Use Case 1 - Game Initialization
 *
 * This is the Game Initialization
 * This displays all the rules maintained in the Game
 *
 * @author Naveen Kumar
 * @version 1.0
 *
 * */

package org.example.guessingapp;

import java.util.Random;

public class GameConfig  {

        private final int MIN = 1;
        private final int MAX = 100;
        private final int MAX_ATTEMPTS = 7;
        private final int MAX_HINTS = 3;
        private int targetNumber;

    public GameConfig() {
        Random random = new Random();
        this.targetNumber = random.nextInt(MAX - MIN + 1) + MIN;
    }

    public int getTargetNumber() {
        return targetNumber;
    }
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }
    public int getMaxHints() {
        return MAX_HINTS;
    }

    public void showRules() {
        System.out.println("Guess a number between " + MIN + " and " + MAX);
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");
        System.out.println("Hints will be provided after wrong guesses.");
        System.out.println();
    }

}
