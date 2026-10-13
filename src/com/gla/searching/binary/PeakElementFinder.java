package com.gla.searching.binary;

public class PeakElementFinder {

    public static int findPeak(int[] arr) {
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
        System.out.println("Peak index: " + peak + " value: " + arr[peak]);
    }
}
