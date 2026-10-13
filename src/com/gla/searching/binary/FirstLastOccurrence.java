package com.gla.searching.binary;

import java.util.Arrays;

/**
 * Binary search for first and last occurrence of a target in a sorted array.
 */
public class FirstLastOccurrence {

    public static int findFirst(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int findLast(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int[] findFirstLast(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        return new int[]{findFirst(arr, target), findLast(arr, target)};
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] res = findFirstLast(arr, target);
        System.out.println("Array: " + Arrays.toString(arr) + ", target=" + target);
        System.out.println("First occurrence: " + res[0] + ", Last occurrence: " + res[1]);

        int target2 = 6;
        int[] res2 = findFirstLast(arr, target2);
        System.out.println("Array: " + Arrays.toString(arr) + ", target=" + target2);
        System.out.println("First occurrence: " + res2[0] + ", Last occurrence: " + res2[1]);
    }
}
