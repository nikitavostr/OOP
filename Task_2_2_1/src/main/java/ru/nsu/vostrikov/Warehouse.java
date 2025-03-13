package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Warehouse class.
 */
public class Warehouse {
    private final Queue<PizzaOrder> orders;
    private final int capacity;

    /**
     * Constructor.
     */
    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.orders = new LinkedList<>();
    }

    /**
     * Add order.
     */
    public synchronized void addOrder(PizzaOrder order) throws InterruptedException {
        while (orders.size() >= capacity) {
            wait();
        }
        orders.add(order);
        notifyAll();
    }

    /**
     * Take orders.
     */
    public synchronized List<PizzaOrder> takeOrders(int n) throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        List<PizzaOrder> takenOrders = new ArrayList<>();
        for (int i = 0; i < n && !orders.isEmpty(); i++) {
            takenOrders.add(orders.poll());
        }
        notifyAll();
        return takenOrders;
    }
}
