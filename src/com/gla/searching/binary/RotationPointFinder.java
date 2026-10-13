package com.gla.searching.binary;

public class RotationPointFinder {

    public static int findRotationPoint(int[] arr) {
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
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int idx = findRotationPoint(arr);
        System.out.println("Rotation index: " + idx + " value: " + arr[idx]);
        int[] arr2 = {0, 1, 2, 4, 5, 6, 7};
        int idx2 = findRotationPoint(arr2);
        System.out.println("Rotation index: " + idx2 + " value: " + arr2[idx2]);
    }
}
