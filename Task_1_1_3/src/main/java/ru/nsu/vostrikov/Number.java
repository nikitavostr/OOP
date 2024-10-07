package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Number class
 */
class Number extends Expression {
    private final int value;

    /**
     * constructor
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * convert to string
     */
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * calculate variables
     */
    public Double calculate(HashMap<String, Double> dictVars) {
        return (double) value;
    }

    /**
     * find derivative
     */
    public Expression derivative(String variable) {
        return new Number(0);
    }
}
