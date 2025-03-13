package ru.nsu.vostrikov;

/**
 * Pizzeria class.
 */
public class Pizzeria {
    private final Warehouse warehouse;
    private final OrderQueue orderQueue;
    private final Bakery[] bakers;
    private final Courier[] couriers;
    private boolean open = true;

    /**
     * Constructor.
     */
    public Pizzeria(int n, int m, int t, int[] bakerSpeeds, int[] courierCapacities) {
        this.warehouse = new Warehouse(t);
        this.bakers = new Bakery[n];
        this.orderQueue = new OrderQueue();
        for (int i = 0; i < n; i++) {
            bakers[i] = new Bakery(i, bakerSpeeds[i], warehouse, orderQueue);
        }
        this.couriers = new Courier[m];
        for (int i = 0; i < m; i++) {
            couriers[i] = new Courier(i, courierCapacities[i], warehouse, this);
        }
    }

    /**
     * Start function.
     */
    public void start() {
        for (Bakery bakery : bakers) {
            bakery.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    /**
     * Place order.
     */
    public void placeOrder(PizzaOrder order) {
        if (!open) {
            order.setState(OrderStatus.DECLINED);
            System.out.println(order);
            return;
        }
        orderQueue.addOrder(order);
    }

    /**
     * Order delivered.
     */
    public synchronized void orderDelivered(PizzaOrder order) {
        order.setState(OrderStatus.DELIVERED);
        System.out.println(order);
        orderQueue.activeOrders--;
    }

    /**
     * Working or not.
     */
    public synchronized boolean working() {
        synchronized (warehouse) {
            return open || orderQueue.activeOrders != 0;
        }
    }

    /**
     * Close pizzeria.
     */
    public void closePizzeria() throws InterruptedException {
        open = false;
        while (working()) {
            Thread.sleep(500);
        }
        for (Bakery bakery : bakers) {
            bakery.interrupt();
        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }
    }

}
