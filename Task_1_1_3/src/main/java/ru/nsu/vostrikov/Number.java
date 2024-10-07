package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Number class.
 */
class Number extends Expression {
    private final int value;

    /**
     * constructor.
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * convert to string.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * calculate variables.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) {
        return (double) value;
    }

    /**
     * find derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }
}
