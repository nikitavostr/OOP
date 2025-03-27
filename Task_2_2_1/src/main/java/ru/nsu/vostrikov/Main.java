package ru.nsu.vostrikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

/**
 * Main class.
 */
public class Main {

    /**
     * Main function.
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.json");
        if (inputStream == null) {
            throw new IOException("file not found");
        }
        PizzeriaConfig config = objectMapper.readValue(inputStream, PizzeriaConfig.class);

        Pizzeria pizzeria = new Pizzeria(
                config.getBakers(),
                config.getCouriers(),
                config.getStorageSize(),
                config.getBakerySpeeds(),
                config.getCourierCap()
        );

        pizzeria.start();
        long startTime = System.currentTimeMillis();
        int id = 1;
        while (System.currentTimeMillis() - startTime < config.getWorkTime() * 1000L) {
            pizzeria.placeOrder(new PizzaOrder(id));
            id++;
            Thread.sleep(500);
        }

        pizzeria.closePizzeria();

        System.out.println("All orders are completed");
    }
}