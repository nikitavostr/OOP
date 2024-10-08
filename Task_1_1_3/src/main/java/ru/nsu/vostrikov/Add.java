package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Addition class.
 */
class Add extends Expression {
    private final Expression first;
    private final Expression second;

    /**
     * constructor.
     *
     * @param first  - first exp.
     * @param second - second exp.
     */
    public Add(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * convert to string.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + "+" + second.toString() + ")";
    }

    /**
     * find derivative.
     *
     * @param variable - variable of derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(first.derivative(variable), second.derivative(variable));
    }

    /**
     * calculate add.
     *
     * @param dictVars - dict of variables.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) throws Exception {
        return first.calculate(dictVars) + second.calculate(dictVars);
    }
}
