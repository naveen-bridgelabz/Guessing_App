/**
 * UC5: Game Result Model
 *
 * Stores result details of a game
 *
 * @author Naveen Kumar
 * @version 1.5
 *
 */

package org.example.guessingapp;

public class GameResult {

    private final String playerName;
    private final int attempts;
    private final boolean isWin;

    public GameResult(String playerName, int attempts, boolean isWin) {
        this.playerName = playerName;
        this.attempts = attempts;
        this.isWin = isWin;
    }

    public String formatForFile() {
        return "Player: " + playerName +
                " | Attempts: " + attempts +
                " | Result: " + (isWin ? "WIN" : "LOSS");
    }
}
