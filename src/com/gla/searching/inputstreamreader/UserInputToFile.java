package com.gla.searching.inputstreamreader;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reads user input via BufferedReader(InputStreamReader(System.in))
 * until "exit" and appends lines to a file via FileWriter.
 */
public class UserInputToFile {

    public static void main(String[] args) {
        String filePath = "userinput.txt";
        if (args.length > 0) {
            filePath = args[0];
        }
        System.out.println("Type lines to append to " + filePath + ". Type \"exit\" to quit.");
        try (BufferedReader consoleReader =
                     new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(filePath, true)) {
            String line;
            while ((line = consoleReader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting. Lines saved to " + filePath);
                    break;
                }
                writer.write(line + System.lineSeparator());
                writer.flush();
                System.out.println("Saved: " + line);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
