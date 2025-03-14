package ru.nsu.vostrikov;

/**
 * Order class.
 */
class PizzaOrder {
    private final int id;
    private OrderStatus state;

    /**
     * Constructor.
     */
    public PizzaOrder(int id) {
        this.id = id;
        this.state = OrderStatus.CREATED;
    }

    /**
     * Get id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get state.
     */
    public OrderStatus getState() {
        return state;
    }

    /**
     * Set state.
     */
    public void setState(OrderStatus state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + state;
    }
}
