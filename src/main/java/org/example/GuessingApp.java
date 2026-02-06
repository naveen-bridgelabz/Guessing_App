package org.example;

import org.example.guessingapp.GameConfig;
import org.example.guessingapp.GuessValidator;
import org.example.guessingapp.HintGenerator;

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

        // UC2: Game loop
        while (attempt < config.getMaxAttempts()) {

            System.out.print("Enter your number guess: ");
            int guess = input.nextInt();
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
                break;
            }
        }

        input.close();
    }
}
