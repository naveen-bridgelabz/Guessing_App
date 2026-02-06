/*
*
* Use Case 2 - User Guess Validation
*
* Guessing App - Validator Initialised
* Target number is given using SCANNER
*
* @author Naveen Kumar
* @version 1.1
*
* */

package org.example.guessingapp;

public class GuessValidator {
    public static String validateGuess(int guess, int target){
        if(guess == target){
            return "Correct";
        } else if(guess < target){
            return "Low";
        }else {
            return "High";
        }
    }
}
