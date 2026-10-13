package com.gla.sorting;

import java.util.Arrays;

/**
 * Bubble Sort for student marks with early-exit optimization.
 * Stops early if a full pass makes no swaps (array already sorted).
 */
public class BubbleSortStudentMarks {

    /** Sorts the array in ascending order using bubble sort. */
    public static void sort(int[] marks) {
        if (marks == null || marks.length < 2) {
            return;
        }
        int n = marks.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // Last i elements are already in place
            for (int j = 0; j < n - 1 - i; j++) {
                if (marks[j] > marks[j + 1]) {
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }
            // Early exit: no swaps means sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] marks = {85, 72, 90, 66, 78, 95, 60};
        System.out.println("Before: " + Arrays.toString(marks));
        sort(marks);
        System.out.println("After:  " + Arrays.toString(marks));
    }
}
