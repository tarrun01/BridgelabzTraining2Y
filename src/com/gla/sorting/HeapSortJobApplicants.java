package com.gla.sorting;

import java.util.Arrays;

public class HeapSortJobApplicants {

    public static void heapSort(int[] arr) {
        int n = arr.length;
        // build max heap first
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // take out biggest one by one
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int big = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        // find biggest among parent and children
        if (left < n && arr[left] > arr[big]) {
            big = left;
        }
        if (right < n && arr[right] > arr[big]) {
            big = right;
        }
        if (big != i) {
            int temp = arr[i];
            arr[i] = arr[big];
            arr[big] = temp;
            heapify(arr, n, big);
        }
    }

    public static void main(String[] args) {
        int[] salaries = {50000, 30000, 70000, 40000, 60000};
        System.out.println("Before: " + Arrays.toString(salaries));
        heapSort(salaries);
        System.out.println("After: " + Arrays.toString(salaries));
    }
}
