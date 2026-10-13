package com.gla.searching.inputstreamreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Demonstrates byte-to-char conversion with FileInputStream +
 * InputStreamReader (UTF-8) + BufferedReader.
 */
public class ByteToCharStreamDemo {

    public static void ensureSampleFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(file);
                 java.io.OutputStreamWriter osw =
                         new java.io.OutputStreamWriter(fos, StandardCharsets.UTF_8);
                 java.io.BufferedWriter writer = new java.io.BufferedWriter(osw)) {
                writer.write("Hello, \u4e16\u754c! UTF-8 demo: caf\u00e9, \u20ac, \uD83D\uDE00");
                writer.newLine();
                writer.write("Second line with unicode: \u00e4\u00f6\u00fc");
                writer.newLine();
            }
            System.out.println("Created demo file: " + filePath);
        }
    }

    public static void readAndPrint(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "utf8sample.txt";
        try {
            ensureSampleFile(filePath);
            System.out.println("Reading (UTF-8) file: " + filePath);
            readAndPrint(filePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
