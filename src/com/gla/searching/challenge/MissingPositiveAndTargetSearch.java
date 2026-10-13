package com.gla.searching.challenge;

import java.util.Arrays;

/**
 * Linear search for first missing positive (boolean visited array),
 * then sort + binary search for a target.
 */
public class MissingPositiveAndTargetSearch {

    public static int findFirstMissingPositive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int n = arr.length;
        boolean[] visited = new boolean[n + 1];
        for (int value : arr) {
            if (value >= 1 && value <= n) {
                visited[value] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};
        System.out.println("Array: " + Arrays.toString(arr));
        int missing = findFirstMissingPositive(arr);
        System.out.println("First missing positive: " + missing);

        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted: " + Arrays.toString(sorted));

        int target1 = 4;
        int idx1 = binarySearch(sorted, target1);
        System.out.println("Binary search target " + target1 + ": index=" + idx1);

        int target2 = 2;
        int idx2 = binarySearch(sorted, target2);
        System.out.println("Binary search target " + target2 + ": index=" + idx2);
    }
}
