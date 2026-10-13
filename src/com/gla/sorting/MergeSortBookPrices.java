package com.gla.sorting;

import java.util.Arrays;

/** Merge Sort for book prices (double[]). */
public class MergeSortBookPrices {

    /** Sorts the array in ascending order using merge sort. */
    public static void sort(double[] prices) {
        if (prices == null || prices.length < 2) {
            return;
        }
        mergeSort(prices, 0, prices.length - 1);
    }

    private static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(double[] arr, int left, int mid, int right) {
        double[] temp = new double[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        // Merge the two halves in sorted order
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // Copy back to original array
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        double[] prices = {499.99, 199.50, 349.00, 99.99, 599.49, 249.75};
        System.out.println("Before: " + Arrays.toString(prices));
        sort(prices);
        System.out.println("After:  " + Arrays.toString(prices));
    }
}
