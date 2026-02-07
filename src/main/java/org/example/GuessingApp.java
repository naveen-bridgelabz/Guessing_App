package org.example;

import org.example.guessingapp.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        System.out.println("----------------------------");

        Scanner input = new Scanner(System.in);
        FileStorageService storageService = new FileStorageService();

        System.out.print("Enter your name: ");
        String playerName = input.nextLine();

        boolean playAgain = true;

        // UC6: Restart loop (only used if player LOSES)
        while (playAgain) {

            // UC1: Initialize game (RESET happens here)
            GameConfig config = new GameConfig();
            HintGenerator hintGenerator =
                    new HintGenerator(config.getMaxHints());

            config.showRules();

            int attempt = 0;
            boolean isWin = false;

            // UC2 + UC3 + UC4: Guessing loop
            while (attempt < config.getMaxAttempts()) {

                try {
                    System.out.print("Enter your number guess: ");
                    int guess = input.nextInt();

                    // UC4: Range validation
                    if (guess < config.getMin() || guess > config.getMax()) {
                        System.out.println(
                                "Invalid input! Enter number between "
                                        + config.getMin() + " and " + config.getMax()
                        );
                        continue; // do not count invalid attempt
                    }

                    attempt++;

                    String result = GuessValidator.validateGuess(
                            guess,
                            config.getTargetNumber()
                    );

                    System.out.println(result);

                    // ✅ EXIT IMMEDIATELY ON CORRECT GUESS
                    if ("CORRECT".equals(result)) {
                        System.out.println(
                                "🎉 Congratulations! You guessed the number in "
                                        + attempt + " attempts."
                        );
                        isWin = true;
                        break; // exits guessing loop immediately
                    }

                    // ✅ HINT ONLY FOR WRONG GUESS
                    System.out.println(
                            hintGenerator.generateHint(
                                    config.getTargetNumber()
                            )
                    );

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Numbers only.");
                    input.next(); // clear invalid input
                }
            }

            // UC5: Save game result
            GameResult gameResult =
                    new GameResult(playerName, attempt, isWin);
            storageService.saveResult(gameResult);

            // ✅ EXIT GAME COMPLETELY IF WON
            if (isWin) {
                System.out.println("Game completed successfully. Exiting...");
                break; // exits playAgain loop
            }

            // UC6: Ask replay ONLY if player LOST
            input.nextLine(); // clear buffer
            System.out.print("Do you want to play again? (Y/N): ");
            String choice = input.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
                playAgain = false;
            }
        }

        input.close();
        System.out.println("Thank you for playing. Goodbye!");
    }
}
