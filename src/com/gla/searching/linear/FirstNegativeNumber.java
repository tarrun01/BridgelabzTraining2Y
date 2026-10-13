package com.gla.searching.linear;

public class FirstNegativeNumber {

    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, -2, 8, -1};
        int idx = findFirstNegative(arr);
        System.out.println("First negative index: " + idx);
        if (idx != -1) {
            System.out.println("Value: " + arr[idx]);
        }
        int[] arr2 = {1, 2, 3, 4};
        System.out.println("First negative index: " + findFirstNegative(arr2));
    }
}
