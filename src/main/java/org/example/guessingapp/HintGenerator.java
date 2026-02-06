package org.example.guessingapp;

/*
 * Use Case 3 - Hint Generation
 *
 * @author Naveen Kumar
 * @version 1.2
 */
public class HintGenerator {

    private int hintCount = 0;
    private final int maxHints;

    public HintGenerator(int maxHints) {
        this.maxHints = maxHints;
    }

    public String generateHint(int targetNumber) {

        if (hintCount >= maxHints) {
            return "No more hints available.";
        }

        hintCount++;

        switch (hintCount) {

            case 1:
                return (targetNumber % 2 == 0)
                        ? "Hint: The number is EVEN."
                        : "Hint: The number is ODD.";

            case 2:
                return (targetNumber <= 50)
                        ? "Hint: The number is between 1 and 50."
                        : "Hint: The number is between 51 and 100.";

            case 3:
                return "Hint: The number is "
                        + (targetNumber % 3 == 0
                        ? "divisible by 3."
                        : "NOT divisible by 3.");

            default:
                return "No hint available.";
        }
    }
}
