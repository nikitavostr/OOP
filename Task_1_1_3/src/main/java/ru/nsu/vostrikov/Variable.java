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
    @Override
    public String toString() {
        return name;
    }

    /**
     * find derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(name.equals(variable) ? 1 : 0);
    }

    /**
     * calculate variable.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) throws Exception {
        if (!dictVars.containsKey(name)) {
            throw new Exception(name + " have no value");
        }
        return dictVars.get(name);
    }
}
