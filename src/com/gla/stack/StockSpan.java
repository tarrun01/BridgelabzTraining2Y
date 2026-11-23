package com.gla.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StockSpan {
    public static int[] calculateSpans(int[] prices) {
        int[] spans = new int[prices.length];
        Deque<Integer> higherPriceDays = new ArrayDeque<>();
        for (int day = 0; day < prices.length; day++) {
            while (!higherPriceDays.isEmpty() && prices[higherPriceDays.peek()] <= prices[day]) {
                higherPriceDays.pop();
            }

            int previousHigherDay = higherPriceDays.isEmpty() ? -1 : higherPriceDays.peek();
            spans[day] = day - previousHigherDay;
            higherPriceDays.push(day);
        }
        return spans;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(calculateSpans(prices)));
    }
}
