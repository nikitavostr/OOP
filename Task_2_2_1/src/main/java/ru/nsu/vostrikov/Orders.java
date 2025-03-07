package ru.nsu.vostrikov;

import java.util.LinkedList;
import java.util.Queue;

public class Orders {
    public class OrderQueue {
        private final Queue<PizzaOrder> queue = new LinkedList<>();

        public synchronized PizzaOrder takeOrder() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            return queue.poll();
        }

        public synchronized void addOrder(PizzaOrder order) {
            queue.add(order);
            notifyAll();
        }

        public synchronized boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
