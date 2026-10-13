package com.gla.searching.binary;

/**
 * Binary search for a target in a row-wise sorted matrix treated as 1D array.
 */
public class SearchIn2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = 0;
        int high = rows * cols - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = matrix[mid / cols][mid % cols];
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target1 = 3;
        int target2 = 13;
        System.out.println("Target " + target1 + " found: " + searchMatrix(matrix, target1));
        System.out.println("Target " + target2 + " found: " + searchMatrix(matrix, target2));
    }
}
