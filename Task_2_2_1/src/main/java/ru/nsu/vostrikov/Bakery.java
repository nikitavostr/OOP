package ru.nsu.vostrikov;

/**
 * Bakery class.
 */
public class Bakery extends Thread {
    private final int id;
    private final int speed;
    private final Warehouse warehouse;
    private final OrderQueue orderQueue;

    /**
     * Constructor.
     */
    public Bakery(int id, int speed, Warehouse warehouse, OrderQueue orderQueue) {
        this.id = id;
        this.speed = speed;
        this.warehouse = warehouse;
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                PizzaOrder order = orderQueue.getOrder();
                order.setState(OrderStatus.COOKING);
                System.out.println(order + " bakery id: " + id);
                Thread.sleep(speed * 1000L);
                warehouse.addOrder(order);
                order.setState(OrderStatus.COOKED);
                System.out.println(order);

            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
    }
}
