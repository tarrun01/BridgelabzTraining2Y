package com.gla.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RecursiveStackSort {
    // The smallest element ends up on top, so popping yields ascending order.
    public static void sort(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int top = stack.pop();
        sort(stack);
        insertSorted(stack, top);
    }

    private static void insertSorted(Deque<Integer> stack, int value) {
        if (stack.isEmpty() || value <= stack.peek()) {
            stack.push(value);
            return;
        }

        int top = stack.pop();
        insertSorted(stack, value);
        stack.push(top);
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>(Arrays.asList(4, -1, 3, 3, 2));
        sort(stack);
        System.out.println(stack);
    }
}
