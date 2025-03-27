package ru.nsu.vostrikov;

import java.util.List;

/**
 * Courier class.
 */
public class Courier extends Thread {
    private final int id;
    private final int capacity;
    private final Warehouse warehouse;
    private final Pizzeria pizzeria;

    /**
     * Constructor.
     */
    public Courier(int id, int capacity, Warehouse warehouse, Pizzeria pizzeria) {
        this.id = id;
        this.capacity = capacity;
        this.warehouse = warehouse;
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                List<PizzaOrder> ordersToDeliver = warehouse.takeOrders(capacity);
                for (PizzaOrder order : ordersToDeliver) {
                    order.setState(OrderStatus.DELIVERING);
                    System.out.println(order + " courier id: " + id);
                    pizzeria.orderDelivered(order);
                }
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
    }
}
