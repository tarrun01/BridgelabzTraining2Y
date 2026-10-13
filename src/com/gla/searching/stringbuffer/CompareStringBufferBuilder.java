package com.gla.searching.stringbuffer;

/**
 * Compares concatenation performance of StringBuffer vs StringBuilder.
 */
public class CompareStringBufferBuilder {

    private static final int ITERATIONS = 100000;
    private static final String WORD = "hello";

    public static void main(String[] args) {
        long startBuffer = System.nanoTime();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < ITERATIONS; i++) {
            buffer.append(WORD);
        }
        String bufferResult = buffer.toString();
        long endBuffer = System.nanoTime();
        double bufferMs = (endBuffer - startBuffer) / 1_000_000.0;

        long startBuilder = System.nanoTime();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ITERATIONS; i++) {
            builder.append(WORD);
        }
        String builderResult = builder.toString();
        long endBuilder = System.nanoTime();
        double builderMs = (endBuilder - startBuilder) / 1_000_000.0;

        System.out.println("Iterations: " + ITERATIONS + " x \"" + WORD + "\"");
        System.out.println("StringBuffer length: " + bufferResult.length()
                + ", time: " + String.format("%.3f", bufferMs) + " ms");
        System.out.println("StringBuilder length: " + builderResult.length()
                + ", time: " + String.format("%.3f", builderMs) + " ms");
        System.out.println("Equal results: " + bufferResult.equals(builderResult));
    }
}
