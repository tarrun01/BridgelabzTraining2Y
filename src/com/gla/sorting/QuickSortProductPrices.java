package com.gla.sorting;

import java.util.Arrays;

/** Quick Sort for product prices (double[]), using last element as pivot. */
public class QuickSortProductPrices {

    /** Sorts the array in ascending order using quick sort. */
    public static void sort(double[] prices) {
        if (prices == null || prices.length < 2) {
            return;
        }
        quickSort(prices, 0, prices.length - 1);
    }

    private static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition using last element as pivot; returns final pivot index.
    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        double[] prices = {1299.00, 499.99, 899.50, 199.99, 1499.00, 749.25};
        System.out.println("Before: " + Arrays.toString(prices));
        sort(prices);
        System.out.println("After:  " + Arrays.toString(prices));
    }
}
