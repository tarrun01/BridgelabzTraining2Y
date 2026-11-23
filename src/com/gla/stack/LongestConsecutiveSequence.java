package com.gla.stack;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static int findLength(int[] values) {
        Set<Integer> numbers = new HashSet<>();
        for (int value : values) {
            numbers.add(value);
        }

        int longest = 0;
        for (int start : numbers) {
            if (start != Integer.MIN_VALUE && numbers.contains(start - 1)) {
                continue;
            }

            int end = start;
            while (end != Integer.MAX_VALUE && numbers.contains(end + 1)) {
                end++;
            }
            longest = Math.max(longest, end - start + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] values = {100, 4, 200, 1, 3, 2, 2};
        System.out.println(findLength(values));
    }
}
