package com.gla.sorting;

import java.util.Arrays;

public class QuickSortProductPrices {

    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            // sort left and right parts
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = low - 1;
        // move small values to left side
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
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
        double[] prices = {999.0, 499.50, 1499.99, 299.0, 799.25};
        System.out.println("Before: " + Arrays.toString(prices));
        quickSort(prices, 0, prices.length - 1);
        System.out.println("After: " + Arrays.toString(prices));
    }
}
