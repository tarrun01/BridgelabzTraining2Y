package com.gla.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // Returns two distinct zero-based indices, or an empty array if no pair exists.
    public static int[] findIndices(int[] values, int target) {
        Map<Long, Integer> indices = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer earlierIndex = indices.get((long) target - values[i]);
            if (earlierIndex != null) {
                return new int[]{earlierIndex, i};
            }
            indices.putIfAbsent((long) values[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] values = {2, 7, 11, 15};
        System.out.println(Arrays.toString(findIndices(values, 9)));
    }
}
