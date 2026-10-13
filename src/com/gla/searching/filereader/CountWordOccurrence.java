package com.gla.searching.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Counts occurrences of a target word in a file (case-insensitive).
 */
public class CountWordOccurrence {

    public static void ensureSampleFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Java is fun. Java is powerful." + System.lineSeparator());
                writer.write("I love JAVA programming, java everywhere." + System.lineSeparator());
            }
            System.out.println("Created demo file: " + filePath);
        }
    }

    public static int countOccurrence(String filePath, String target) throws IOException {
        int count = 0;
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(target)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String filePath = "sample.txt";
        String target = "java";
        try {
            ensureSampleFile(filePath);
            int count = countOccurrence(filePath, target);
            System.out.println("Target word: \"" + target + "\"");
            System.out.println("Occurrences in " + filePath + ": " + count);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
