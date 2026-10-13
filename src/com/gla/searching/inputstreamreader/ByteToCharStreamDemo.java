package com.gla.searching.inputstreamreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteToCharStreamDemo {

    public static void main(String[] args) {
        String filePath = "utf8sample.txt";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Hello World\n");
                    writer.write("Second line\n");
                }
                System.out.println("Created file: " + filePath);
            }
            // read bytes as chars with UTF-8
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
