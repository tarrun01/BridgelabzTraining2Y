package com.gla.searching.stringbuilder;

/**
 * Reverses a string using StringBuilder.reverse().
 */
public class ReverseStringBuilder {

    public static String reverse(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String input = "hello";
        String reversed = reverse(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }
}
