package com.gla.searching.stringbuffer;

/**
 * Concatenates a String array using StringBuffer.
 */
public class ConcatenateStringsStringBuffer {

    public static String concatenate(String[] parts) {
        StringBuffer sb = new StringBuffer();
        if (parts != null) {
            for (String part : parts) {
                sb.append(part);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"Hello", " ", "World", "!", " ", "Welcome", " ", "to", " ", "Java."};
        String result = concatenate(words);
        System.out.println("Parts: Hello, World, Welcome to Java.");
        System.out.println("Concatenated: " + result);
    }
}
