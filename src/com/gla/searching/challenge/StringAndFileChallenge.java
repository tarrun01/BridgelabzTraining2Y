package com.gla.searching.challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringAndFileChallenge {

    public static void main(String[] args) {
        int n = 100000;

        long start1 = System.nanoTime();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append("hello");
        }
        long end1 = System.nanoTime();
        System.out.println("StringBuffer length: " + buffer.length() + " time: " + (end1 - start1) / 1000000 + " ms");

        long start2 = System.nanoTime();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append("hello");
        }
        long end2 = System.nanoTime();
        System.out.println("StringBuilder length: " + builder.length() + " time: " + (end2 - start2) / 1000000 + " ms");

        try {
            File file = File.createTempFile("sample", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < 20000; i++) {
                    writer.write("hello world java");
                    writer.newLine();
                }
            }
            System.out.println("Made file: " + file.getAbsolutePath());

            // count words with FileReader
            int count1 = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(" ");
                    count1 = count1 + words.length;
                }
            }
            System.out.println("FileReader words: " + count1);

            // count words with InputStreamReader
            int count2 = 0;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(" ");
                    count2 = count2 + words.length;
                }
            }
            System.out.println("InputStreamReader words: " + count2);

            file.delete();
            System.out.println("Deleted temp file.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
