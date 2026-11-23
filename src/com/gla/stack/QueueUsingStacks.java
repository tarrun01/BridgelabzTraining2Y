package com.gla.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class QueueUsingStacks {
    private final Deque<Integer> incoming = new ArrayDeque<>();
    private final Deque<Integer> outgoing = new ArrayDeque<>();

    public void enqueue(int value) {
        incoming.push(value);
    }

    // Each element moves to outgoing at most once, so dequeue is amortized O(1).
    public int dequeue() {
        moveToOutgoing();
        return outgoing.pop();
    }

    public int peek() {
        moveToOutgoing();
        return outgoing.element();
    }

    public boolean isEmpty() {
        return incoming.isEmpty() && outgoing.isEmpty();
    }

    private void moveToOutgoing() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outgoing.isEmpty()) {
            while (!incoming.isEmpty()) {
                outgoing.push(incoming.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        System.out.println(queue.dequeue());
        queue.enqueue(30);
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
