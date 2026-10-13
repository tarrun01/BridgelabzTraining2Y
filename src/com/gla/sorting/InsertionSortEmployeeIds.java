package com.gla.sorting;

import java.util.Arrays;

public class InsertionSortEmployeeIds {

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        // pick each element and put it in right place
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] ids = {105, 102, 108, 101, 104};
        System.out.println("Before: " + Arrays.toString(ids));
        insertionSort(ids);
        System.out.println("After: " + Arrays.toString(ids));
    }
}
