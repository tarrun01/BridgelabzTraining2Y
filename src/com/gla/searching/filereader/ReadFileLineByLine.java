package com.gla.searching.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFileLineByLine {

    public static void main(String[] args) {
        String filePath = "sample.txt";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Hello World\n");
                    writer.write("This is a sample file.\n");
                    writer.write("Reading line by line.\n");
                }
                System.out.println("Created file: " + filePath);
            }
            // read file line by line
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                int no = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Line " + no + ": " + line);
                    no++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
