package ru.nsu.vostrikov;

public class Bakery extends Thread {
    private final int id;
    private final int speed;
    private final Pizzeria pizzeria;
    private final Warehouse warehouse;

    public Bakery(int id, int speed, Pizzeria pizzeria, Warehouse warehouse) {
        this.id = id;
        this.speed = speed;
        this.pizzeria = pizzeria;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                PizzaOrder order = pizzeria.getOrder();
                order.setState("Готовится пекарем " + id);
                System.out.println(order);
                Thread.sleep(speed * 1000L);
                warehouse.addOrder(order);
                order.setState("Готово");
                System.out.println(order);
                synchronized (pizzeria) {
                    pizzeria.notifyAll();
                }
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
    }
}
