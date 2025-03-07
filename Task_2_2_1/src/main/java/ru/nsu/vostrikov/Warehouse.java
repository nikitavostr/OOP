package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final Queue<PizzaOrder> orders;
    private final int capacity;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.orders = new LinkedList<>();
    }

    public synchronized void addOrder(PizzaOrder order) throws InterruptedException {
        while (orders.size() >= capacity) {
            wait();
        }
        orders.add(order);
        notifyAll();
    }

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

    public synchronized boolean isEmpty() {
        return orders.isEmpty();
    }
}
