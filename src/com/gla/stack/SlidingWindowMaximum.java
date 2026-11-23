package com.gla.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
    public static int[] findMaximums(int[] values, int windowSize) {
        if (windowSize < 1 || windowSize > values.length) {
            throw new IllegalArgumentException("Window size must be between 1 and the array length");
        }

        int[] maximums = new int[values.length - windowSize + 1];
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            if (!candidates.isEmpty() && candidates.peekFirst() <= i - windowSize) {
                candidates.removeFirst();
            }
            while (!candidates.isEmpty() && values[candidates.peekLast()] <= values[i]) {
                candidates.removeLast();
            }

            candidates.addLast(i);
            if (i >= windowSize - 1) {
                maximums[i - windowSize + 1] = values[candidates.peekFirst()];
            }
        }
        return maximums;
    }

    public static void main(String[] args) {
        int[] values = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(findMaximums(values, 3)));
    }
}
