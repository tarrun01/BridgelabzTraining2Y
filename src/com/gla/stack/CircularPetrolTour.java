package com.gla.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class CircularPetrolTour {
    // Returns a zero-based start, or -1 if no tour is possible.
    public static int findStart(int[] petrol, int[] distance) {
        if (petrol.length != distance.length) {
            throw new IllegalArgumentException("Each pump must have a distance to the next pump");
        }
        if (petrol.length == 0) {
            return -1;
        }

        Queue<Integer> pumps = new ArrayDeque<>();
        for (int i = 0; i < petrol.length; i++) {
            pumps.add(i);
        }

        long tank = 0;
        int start = 0;
        int completedLegs = 0;
        // Two passes let a candidate found late in the first pass wrap around.
        for (long i = 0; i < 2L * petrol.length - 1; i++) {
            int pump = pumps.remove();
            pumps.add(pump);
            tank += (long) petrol[pump] - distance[pump];
            if (tank < 0) {
                // Every start in the failed segment also falls short here.
                start = pumps.element();
                tank = 0;
                completedLegs = 0;
            } else {
                completedLegs++;
                if (completedLegs == petrol.length) {
                    return start;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};
        System.out.println(findStart(petrol, distance));
    }
}
