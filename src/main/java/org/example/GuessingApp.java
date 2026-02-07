package org.example;

import org.example.guessingapp.GameConfig;
import org.example.guessingapp.GuessValidator;
import org.example.guessingapp.HintGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        System.out.println("----------------------------");

        // UC1: Initialize game
        GameConfig config = new GameConfig();
        config.showRules();

        // UC3: Initialize HintGenerator ONCE
        HintGenerator hintGenerator =
                new HintGenerator(config.getMaxHints());

        Scanner input = new Scanner(System.in);
        int attempt = 0;

        // UC2 + UC4: Game loop with error handling
        while (attempt < config.getMaxAttempts()) {

            try {
                System.out.print("Enter your number guess: ");
                int guess = input.nextInt();

                // UC4: Range validation
                if (guess < config.getMin() || guess > config.getMax()) {
                    System.out.println(
                            "Invalid input! Please enter a number between "
                                    + config.getMin() + " and " + config.getMax()
                    );
                    continue; // ❗do not count invalid attempts
                }

                attempt++;

                String result = GuessValidator.validateGuess(
                        guess,
                        config.getTargetNumber()
                );

                System.out.println(result);

                // UC3: Generate hint ONLY if guess is wrong
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
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input! Please enter numbers only."
                );
                input.next(); // clear invalid token
            }
        }

        input.close();
        System.out.println("Game Over. Thank you for playing!");
    }
}
