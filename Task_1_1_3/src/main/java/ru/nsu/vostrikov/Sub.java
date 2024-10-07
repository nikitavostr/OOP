package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Subtraction class.
 */
class Sub extends Expression {
    private final Expression first;
    private final Expression second;

    /**
     * constructor.
     */
    public Sub(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * convert to string.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + "-" + second.toString() + ")";
    }

    /**
     * calculate subtraction.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) throws Exception {
        return first.calculate(dictVars) - second.calculate(dictVars);
    }

    /**
     * find derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Sub(first.derivative(variable), second.derivative(variable));
    }

}
