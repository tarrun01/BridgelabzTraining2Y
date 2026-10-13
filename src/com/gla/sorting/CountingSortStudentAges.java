package com.gla.sorting;

import java.util.Arrays;

/**
 * Counting Sort for student ages (typically 10-18).
 * Handles any generic min/max range by offsetting with the minimum value.
 */
public class CountingSortStudentAges {

    /** Sorts the array in ascending order using counting sort. */
    public static void sort(int[] ages) {
        if (ages == null || ages.length < 2) {
            return;
        }
        // Find generic min and max
        int min = ages[0];
        int max = ages[0];
        for (int age : ages) {
            if (age < min) {
                min = age;
            }
            if (age > max) {
                max = age;
            }
        }
        // Count occurrences, offset by min to support any range
        int[] count = new int[max - min + 1];
        for (int age : ages) {
            count[age - min]++;
        }
        // Write back in sorted order
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                ages[index++] = i + min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {15, 12, 18, 10, 14, 16, 12, 18, 11, 15};
        System.out.println("Before: " + Arrays.toString(ages));
        sort(ages);
        System.out.println("After:  " + Arrays.toString(ages));
    }
}
