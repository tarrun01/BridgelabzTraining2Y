package com.gla.sorting;

import java.util.Arrays;

public class MergeSortBookPrices {

    public static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // sort both halves first
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        double[] temp1 = new double[n1];
        double[] temp2 = new double[n2];
        // copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            temp1[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            temp2[j] = arr[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (temp1[i] <= temp2[j]) {
                arr[k] = temp1[i];
                i++;
            } else {
                arr[k] = temp2[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = temp1[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = temp2[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        double[] prices = {299.5, 149.0, 499.99, 99.50, 199.75};
        System.out.println("Before: " + Arrays.toString(prices));
        mergeSort(prices, 0, prices.length - 1);
        System.out.println("After: " + Arrays.toString(prices));
    }
}
