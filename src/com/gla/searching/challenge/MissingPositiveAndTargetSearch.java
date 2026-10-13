package com.gla.searching.challenge;

import java.util.Arrays;

public class MissingPositiveAndTargetSearch {

    // check 1,2,3 one by one if present
    public static int findFirstMissingPositive(int[] arr) {
        for (int i = 1; i <= arr.length + 1; i++) {
            boolean found = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }
        return 1;
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
        System.out.println("Missing positive: " + findFirstMissingPositive(arr));
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        System.out.println("Search 4: " + binarySearch(sorted, 4));
        System.out.println("Search 2: " + binarySearch(sorted, 2));
    }
}
