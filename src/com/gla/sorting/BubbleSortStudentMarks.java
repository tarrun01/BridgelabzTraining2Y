package com.gla.sorting;

import java.util.Arrays;

public class BubbleSortStudentMarks {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // compare neighbours and push big values to end
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] marks = {85, 72, 90, 66, 78};
        System.out.println("Before: " + Arrays.toString(marks));
        bubbleSort(marks);
        System.out.println("After: " + Arrays.toString(marks));
    }
}
