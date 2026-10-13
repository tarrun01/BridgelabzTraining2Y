package com.gla.searching.stringbuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Removes duplicate characters preserving order using HashSet + StringBuilder.
 */
public class RemoveDuplicatesStringBuilder {

    public static String removeDuplicates(String input) {
        if (input == null) {
            return null;
        }
        Set<Character> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (seen.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "programming";
        String result = removeDuplicates(input);
        System.out.println("Original: " + input);
        System.out.println("Without duplicates: " + result);

        String input2 = "hello world";
        System.out.println("Original: " + input2);
        System.out.println("Without duplicates: " + removeDuplicates(input2));
    }
}
