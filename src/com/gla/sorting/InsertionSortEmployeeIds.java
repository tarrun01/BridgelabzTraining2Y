package com.gla.sorting;

import java.util.Arrays;

/** Insertion Sort for employee IDs. */
public class InsertionSortEmployeeIds {

    /** Sorts the array in ascending order using insertion sort. */
    public static void sort(int[] ids) {
        if (ids == null || ids.length < 2) {
            return;
        }
        for (int i = 1; i < ids.length; i++) {
            int key = ids[i];
            int j = i - 1;
            // Shift larger elements one position right
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] ids = {105, 102, 108, 101, 107, 103};
        System.out.println("Before: " + Arrays.toString(ids));
        sort(ids);
        System.out.println("After:  " + Arrays.toString(ids));
    }
}
