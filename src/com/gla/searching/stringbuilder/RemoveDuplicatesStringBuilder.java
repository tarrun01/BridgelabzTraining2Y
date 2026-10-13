package com.gla.searching.stringbuilder;

import java.util.HashSet;

public class RemoveDuplicatesStringBuilder {

    // remove duplicate chars and keep first order
    public static String removeDuplicates(String input) {
        HashSet<Character> seen = new HashSet<Character>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "programming";
        System.out.println("Original: " + input);
        System.out.println("Without duplicates: " + removeDuplicates(input));
        String input2 = "hello world";
        System.out.println("Original: " + input2);
        System.out.println("Without duplicates: " + removeDuplicates(input2));
    }
}
