package com.gla.sorting;

import java.util.Arrays;

/** Heap Sort for job applicants' salary demands using a max-heap. */
public class HeapSortJobApplicants {

    /** Sorts the array in ascending order using heap sort. */
    public static void sort(int[] salaries) {
        if (salaries == null || salaries.length < 2) {
            return;
        }
        int n = salaries.length;
        // Build max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(salaries, n, i);
        }
        // Extract elements one by one from the heap
        for (int i = n - 1; i > 0; i--) {
            int temp = salaries[0];
            salaries[0] = salaries[i];
            salaries[i] = temp;
            heapify(salaries, i, 0);
        }
    }

    // Ensures subtree rooted at i is a max-heap within heap size n.
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] salaries = {55000, 75000, 60000, 90000, 50000, 80000};
        System.out.println("Before: " + Arrays.toString(salaries));
        sort(salaries);
        System.out.println("After:  " + Arrays.toString(salaries));
    }
}
