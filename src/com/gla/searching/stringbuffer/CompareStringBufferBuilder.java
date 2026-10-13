package com.gla.searching.stringbuffer;

public class CompareStringBufferBuilder {

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
    }
}
