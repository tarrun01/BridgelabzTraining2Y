package com.gla.searching.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CountWordOccurrence {

    public static void main(String[] args) {
        String filePath = "wordcount.txt";
        String target = "java";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Java is fun. Java is powerful.\n");
                    writer.write("I love JAVA programming java everywhere.\n");
                }
                System.out.println("Created file: " + filePath);
            }
            int count = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (int i = 0; i < words.length; i++) {
                        String w = words[i].replace(",", "").replace(".", "");
                        if (w.equalsIgnoreCase(target)) {
                            count++;
                        }
                    }
                }
            }
            System.out.println("Word: " + target);
            System.out.println("Count in " + filePath + ": " + count);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
