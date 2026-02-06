package org.example;

import org.example.guessingapp.GameConfig;
import org.example.guessingapp.GuessValidator;

import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        System.out.println("----------------------------");

        // Create GameConfig object
        GameConfig config = new GameConfig();

        // Display rules
        config.showRules();

        // UC1 ends here

        Scanner input = new Scanner(System.in);
        int attempt = 0;

        while ( attempt < config.getMaxAttempts() ){
            System.out.println("Enter your number Guess : ");
            int guess = input.nextInt();
            attempt++;

            String result = GuessValidator.validateGuess(
              guess, config.getTargetNumber()
            );

            System.out.println(result);

            if("Result".equals(result)){
                break;
            }
        }
        input.close();

    }
}
