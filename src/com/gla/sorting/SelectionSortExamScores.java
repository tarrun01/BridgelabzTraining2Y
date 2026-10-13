package com.gla.sorting;

import java.util.Arrays;

/** Selection Sort for exam scores. */
public class SelectionSortExamScores {

    /** Sorts the array in ascending order using selection sort. */
    public static void sort(int[] scores) {
        if (scores == null || scores.length < 2) {
            return;
        }
        for (int i = 0; i < scores.length - 1; i++) {
            int minIndex = i;
            // Find minimum in unsorted part
            for (int j = i + 1; j < scores.length; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap with first unsorted element
            int temp = scores[i];
            scores[i] = scores[minIndex];
            scores[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] scores = {88, 45, 92, 67, 73, 100, 55};
        System.out.println("Before: " + Arrays.toString(scores));
        sort(scores);
        System.out.println("After:  " + Arrays.toString(scores));
    }
}
