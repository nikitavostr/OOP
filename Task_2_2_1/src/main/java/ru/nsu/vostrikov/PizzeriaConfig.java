package ru.nsu.vostrikov;

/**
 * Pizzeria config.
 */
public class PizzeriaConfig {
    private int bakers;
    private int couriers;
    private int storageSize;
    private int workTime;
    private int[] bakerySpeeds;
    private int[] courierCap;

    /**
     * Get bakers.
     */
    public int getBakers() {
        return bakers;
    }

    /**
     * Get couriers.
     */
    public int getCouriers() {
        return couriers;
    }

    /**
     * Get storage size.
     */
    public int getStorageSize() {
        return storageSize;
    }

    /**
     * Get work time.
     */
    public int getWorkTime() {
        return workTime;
    }

    /**
     * Get bakery speeds.
     */
    public int[] getBakerySpeeds() {
        return bakerySpeeds;
    }

    /**
     * Get couriers cap.
     */
    public int[] getCourierCap() {
        return courierCap;
    }
}
