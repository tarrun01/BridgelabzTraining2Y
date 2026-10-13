package com.gla.searching.binary;

/**
 * Binary search for a peak element (element greater than neighbours).
 */
public class PeakElementFinder {

    public static int findPeak(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        int peak = findPeak(arr);
        System.out.println("Array: [1, 2, 1, 3, 5, 6, 4]");
        System.out.println("Peak index: " + peak + " (value=" + arr[peak] + ")");

        int[] arr2 = {1, 2, 3, 4, 5};
        int peak2 = findPeak(arr2);
        System.out.println("Array: [1, 2, 3, 4, 5]");
        System.out.println("Peak index: " + peak2 + " (value=" + arr2[peak2] + ")");
    }
}
