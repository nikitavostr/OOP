package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarehouseTest {
    private Warehouse warehouse;
    private PizzaOrder order;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse(10);
        order = new PizzaOrder(1);
    }

    @Test
    void testAddOrder() throws InterruptedException {
        warehouse.addOrder(order);
        assertFalse(warehouse.isEmpty());
    }

    @Test
    void testTakeOrders() throws InterruptedException {
        warehouse.addOrder(order);
        PizzaOrder order2 = new PizzaOrder(2);
        warehouse.addOrder(order2);
        List<PizzaOrder> takenOrders = warehouse.takeOrders(2);
        assertEquals(2, takenOrders.size());
        assertTrue(warehouse.isEmpty());
    }

    @Test
    void testIsEmpty() throws InterruptedException {
        assertTrue(warehouse.isEmpty());
        warehouse.addOrder(order);
        assertFalse(warehouse.isEmpty());
        warehouse.takeOrders(1);
        assertTrue(warehouse.isEmpty());
    }
}