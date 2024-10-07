package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Variable class.
 */
class Variable extends Expression {
    private final String name;

    /**
     * constructor.
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * convert to string.
     */
    public String toString() {
        return name;
    }

    /**
     * find derivative.
     */
    public Expression derivative(String variable) {
        return new Number(name.equals(variable) ? 1 : 0);
    }

    /**
     * calculate variable.
     */
    public Double calculate(HashMap<String, Double> dictVars) {
        return dictVars.get(name);
    }
}
