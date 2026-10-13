package com.gla.searching.challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Challenge: 1M concatenation timing comparison plus large temp file
 * word counting via FileReader and InputStreamReader with timing.
 */
public class StringAndFileChallenge {

    private static final int CONCAT_COUNT = 1000000;
    private static final int FILE_WORDS = 200000;

    public static void stringConcatTiming() {
        long startBuffer = System.nanoTime();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < CONCAT_COUNT; i++) {
            buffer.append("hello");
        }
        long endBuffer = System.nanoTime();
        double bufferMs = (endBuffer - startBuffer) / 1_000_000.0;

        long startBuilder = System.nanoTime();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < CONCAT_COUNT; i++) {
            builder.append("hello");
        }
        long endBuilder = System.nanoTime();
        double builderMs = (endBuilder - startBuilder) / 1_000_000.0;

        System.out.println("Concatenations: " + CONCAT_COUNT + " x \"hello\"");
        System.out.println("StringBuffer: " + String.format("%.3f", bufferMs)
                + " ms, length=" + buffer.length());
        System.out.println("StringBuilder: " + String.format("%.3f", builderMs)
                + " ms, length=" + builder.length());
    }

    public static File createLargeTempFile() throws IOException {
        File temp = File.createTempFile("large_sample", ".txt");
        try (FileWriter fw = new FileWriter(temp);
             BufferedWriter writer = new BufferedWriter(fw)) {
            for (int i = 0; i < FILE_WORDS; i++) {
                writer.write("hello world java ");
                if (i % 10 == 9) {
                    writer.newLine();
                }
            }
        }
        System.out.println("Created temp file: " + temp.getAbsolutePath());
        return temp;
    }

    public static long countWordsWithFileReader(File file) throws IOException {
        long count = 0;
        try (FileReader fr = new FileReader(file);
             BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    count += trimmed.split("\\s+").length;
                }
            }
        }
        return count;
    }

    public static long countWordsWithInputStreamReader(File file) throws IOException {
        long count = 0;
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    count += trimmed.split("\\s+").length;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        stringConcatTiming();
        try {
            File temp = createLargeTempFile();

            long start1 = System.nanoTime();
            long count1 = countWordsWithFileReader(temp);
            long end1 = System.nanoTime();
            System.out.println("FileReader word count: " + count1
                    + ", time: " + String.format("%.3f", (end1 - start1) / 1_000_000.0) + " ms");

            long start2 = System.nanoTime();
            long count2 = countWordsWithInputStreamReader(temp);
            long end2 = System.nanoTime();
            System.out.println("InputStreamReader word count: " + count2
                    + ", time: " + String.format("%.3f", (end2 - start2) / 1_000_000.0) + " ms");

            if (temp.delete()) {
                System.out.println("Deleted temp file.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
