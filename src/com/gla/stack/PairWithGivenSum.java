package com.gla.stack;

import java.util.HashSet;
import java.util.Set;

public class PairWithGivenSum {
    public static boolean hasPair(int[] values, int target) {
        Set<Long> seen = new HashSet<>();
        for (int value : values) {
            if (seen.contains((long) target - value)) {
                return true;
            }
            seen.add((long) value);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] values = {8, 4, 1, 6};
        System.out.println(hasPair(values, 10));
    }
}
