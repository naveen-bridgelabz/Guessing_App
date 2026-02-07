package org.example.guessingapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * UC5: File Storage Service
 *
 * Handles persistence of game results
 */

public class FileStorageService {

    private static final String FILE_NAME = "game_results.txt";

    public void saveResult(GameResult result) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("Final_Result", true))) {

            writer.write(result.formatForFile());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving game result!");
        }
    }
}
