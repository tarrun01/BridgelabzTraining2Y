package com.gla.searching.stringbuffer;

public class ConcatenateStringsStringBuffer {

    public static String concatenate(String[] parts) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"Hello", " ", "World", "!", " ", "Welcome", " ", "to", " ", "Java."};
        String result = concatenate(words);
        System.out.println("Concatenated: " + result);
    }
}
