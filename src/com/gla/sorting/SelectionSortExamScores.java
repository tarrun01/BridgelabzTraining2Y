package com.gla.sorting;

import java.util.Arrays;

public class SelectionSortExamScores {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        // find smallest and put it in front
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] scores = {88, 65, 92, 70, 77};
        System.out.println("Before: " + Arrays.toString(scores));
        selectionSort(scores);
        System.out.println("After: " + Arrays.toString(scores));
    }
}
