package com.gla.searching.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates a demo sample.txt if missing, then reads it line by line
 * using FileReader + BufferedReader.
 */
public class ReadFileLineByLine {

    public static void ensureSampleFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Hello World" + System.lineSeparator());
                writer.write("This is a sample file." + System.lineSeparator());
                writer.write("Reading line by line with FileReader." + System.lineSeparator());
            }
            System.out.println("Created demo file: " + filePath);
        }
    }

    public static void readFile(String filePath) throws IOException {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            int lineNo = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNo + ": " + line);
                lineNo++;
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "sample.txt";
        try {
            ensureSampleFile(filePath);
            System.out.println("Reading file: " + filePath);
            readFile(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
