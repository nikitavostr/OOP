package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PizzeriaTest {
    private Pizzeria pizzeria;
    private Warehouse warehouse;
    private PizzaOrder order;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse(10);
        pizzeria = new Pizzeria(1, 1, 10, new int[]{1}, new int[]{5});
        order = new PizzaOrder(1); // Создаем заказ
    }

    @Test
    void testOrderProcessing() throws InterruptedException {
        pizzeria.placeOrder(order);
        pizzeria.start();
        Thread.sleep(4000);
        assertEquals("Завершен", order.getState());
        assertTrue(warehouse.isEmpty());
        pizzeria.stop();
    }

    @Test
    void testClosePizzeria() throws InterruptedException {
        pizzeria.closePizzeria();
        PizzaOrder closedOrder = new PizzaOrder(2);
        pizzeria.placeOrder(closedOrder);
        assertEquals("Заказ отклонен", closedOrder.getState());
        pizzeria.stop();
    }

    @Test
    void testWorkingStatus() throws InterruptedException {
        pizzeria.start();
        pizzeria.placeOrder(order);
        pizzeria.closePizzeria();
        assertTrue(pizzeria.working());
        Thread.sleep(4000);
        assertFalse(pizzeria.working());
        pizzeria.stop();
    }
}