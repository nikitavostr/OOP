package ru.nsu.vostrikov;

import java.util.LinkedList;
import java.util.Queue;

public class Pizzeria {
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> orderQueue = new LinkedList<>();
    private final Bakery[] bakers;
    private final Courier[] couriers;
    private boolean open = true;
    private int activeOrders = 0;

    public Pizzeria(int n, int m, int t, int[] bakerSpeeds, int[] courierCapacities) {
        this.warehouse = new Warehouse(t);
        this.bakers = new Bakery[n];
        for (int i = 0; i < n; i++) {
            bakers[i] = new Bakery(i, bakerSpeeds[i], this, warehouse);
        }
        this.couriers = new Courier[m];
        for (int i = 0; i < m; i++) {
            couriers[i] = new Courier(i, courierCapacities[i], warehouse, this);
        }
    }

    public void start() {
        for (Bakery bakery : bakers) {
            bakery.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    public void placeOrder(PizzaOrder order) {
        if (!open) {
            order.setState("Заказ отклонен");
            System.out.println(order);
            return;
        }
        System.out.println(order);
        orderQueue.add(order);
        synchronized (this) {
            activeOrders++;
            notifyAll();
        }
    }

    public synchronized PizzaOrder getOrder() throws InterruptedException {
        while (orderQueue.isEmpty()) {
            wait();
        }
        return orderQueue.poll();
    }

    public synchronized void orderDelivered(PizzaOrder order) {
        order.setState("Завершен");
        System.out.println(order);
        activeOrders--;
    }

    public synchronized boolean working() {
        synchronized (warehouse) {
            return open || activeOrders != 0;
        }
    }

    public void stop() {
        for (Bakery bakery : bakers) {
            bakery.interrupt();
        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }
    }

    public void closePizzeria() {
        open = false;
    }
}
