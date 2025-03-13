package ru.nsu.vostrikov;

import java.util.LinkedList;
import java.util.Queue;

/**
 * OrderQueue class.
 */
public class OrderQueue {
    private final Queue<PizzaOrder> orderQueue = new LinkedList<>();
    public int activeOrders = 0;

    /**
     * Add order function.
     */
    public synchronized void addOrder(PizzaOrder order) {
        System.out.println(order);
        orderQueue.add(order);
        activeOrders++;
        notifyAll();
    }

    /**
     * Get order function.
     */
    public synchronized PizzaOrder getOrder() throws InterruptedException {
        while (orderQueue.isEmpty()) {
            wait();
        }
        return orderQueue.poll();
    }
}
