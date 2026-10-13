package com.gla.searching.binary;

/**
 * Binary search for the smallest element (rotation point)
 * in a rotated sorted array.
 */
public class RotationPointFinder {

    public static int findRotationPoint(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        int idx = findRotationPoint(rotated);
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Rotation point index: " + idx + " (value=" + rotated[idx] + ")");

        int[] sorted = {0, 1, 2, 4, 5, 6, 7};
        int idx2 = findRotationPoint(sorted);
        System.out.println("Array: [0, 1, 2, 4, 5, 6, 7]");
        System.out.println("Rotation point index: " + idx2 + " (value=" + sorted[idx2] + ")");
    }
}
