package com.gla.sorting;

import java.util.Arrays;

public class CountingSortStudentAges {

    public static void countingSort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        // find min and max age
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        int k = 0;
        // write back ages in order
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[k] = i + min;
                k++;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {15, 12, 18, 10, 14, 12, 16};
        System.out.println("Before: " + Arrays.toString(ages));
        countingSort(ages);
        System.out.println("After: " + Arrays.toString(ages));
    }
}
