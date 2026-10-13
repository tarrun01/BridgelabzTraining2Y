package com.gla.searching.linear;

/**
 * Linear search for the first negative number; returns index or -1.
 */
public class FirstNegativeNumber {

    public static int findFirstNegative(int[] arr) {
        if (arr == null) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 5, 7, -2, 8, -1};
        int[] arr2 = {1, 2, 3, 4};

        int idx1 = findFirstNegative(arr1);
        System.out.println("Array: [3, 5, 7, -2, 8, -1]");
        System.out.println("First negative index: " + idx1
                + (idx1 != -1 ? " (value=" + arr1[idx1] + ")" : " (none found)"));

        int idx2 = findFirstNegative(arr2);
        System.out.println("Array: [1, 2, 3, 4]");
        System.out.println("First negative index: " + idx2);
    }
}
