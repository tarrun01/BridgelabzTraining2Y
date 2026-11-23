package com.gla.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZeroSumSubarrays {
    // Each result is a pair of inclusive, zero-based indices.
    public static List<int[]> findRanges(int[] values) {
        List<int[]> ranges = new ArrayList<>();
        Map<Long, List<Integer>> prefixIndices = new HashMap<>();
        prefixIndices.computeIfAbsent(0L, sum -> new ArrayList<>()).add(-1);

        long prefixSum = 0;
        for (int end = 0; end < values.length; end++) {
            prefixSum += values[end];
            List<Integer> earlierIndices = prefixIndices.computeIfAbsent(
                    prefixSum, sum -> new ArrayList<>());
            for (int earlierIndex : earlierIndices) {
                ranges.add(new int[]{earlierIndex + 1, end});
            }
            earlierIndices.add(end);
        }
        return ranges;
    }

    public static void main(String[] args) {
        int[] values = {3, -1, -2, 0, 3, -3};
        for (int[] range : findRanges(values)) {
            System.out.printf("[%d, %d]%n", range[0], range[1]);
        }
    }
}
