package org.example;

import org.example.guessingapp.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        System.out.println("----------------------------");

        Scanner input = new Scanner(System.in);

        // UC5: Player name
        System.out.print("Enter your name: ");
        String playerName = input.nextLine();

        // UC1: Initialize game
        GameConfig config = new GameConfig();
        config.showRules();

        // UC3: Hint generator
        HintGenerator hintGenerator =
                new HintGenerator(config.getMaxHints());

        // UC5: File storage service
        FileStorageService storageService =
                new FileStorageService();

        int attempt = 0;
        boolean isWin = false;

        // UC2 + UC3 + UC4: Game loop
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
                    continue;
                }

                attempt++;

                String result = GuessValidator.validateGuess(
                        guess,
                        config.getTargetNumber()
                );

                System.out.println(result);

                if (!"CORRECT".equals(result)) {
                    System.out.println(
                            hintGenerator.generateHint(
                                    config.getTargetNumber()
                            )
                    );
                } else {
                    System.out.println(
                            "Congratulations! You guessed the number in "
                                    + attempt + " attempts."
                    );
                    isWin = true;
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numbers only.");
                input.next();
            }
        }

        // UC5: Save game result
        GameResult gameResult =
                new GameResult(playerName, attempt, isWin);

        storageService.saveResult(gameResult);

        input.close();
        System.out.println("Game result saved successfully.");
        System.out.println("Game Over. Thank you for playing!");
    }
}
