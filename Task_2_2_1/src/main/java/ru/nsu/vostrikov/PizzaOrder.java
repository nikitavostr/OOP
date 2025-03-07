package ru.nsu.vostrikov;

class PizzaOrder {
    private final int id;
    private String state;

    public PizzaOrder(int id) {
        this.id = id;
        this.state = "Создан";
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + state;
    }
}
