package ru.nsu.vostrikov;

import java.util.HashMap;

class Mul extends Expression {
    private final Expression first;
    private final Expression second;

    /**
     * constructor.
     */
    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * convert to string.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + "*" + second.toString() + ")";
    }

    /**
     * find derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable)));
    }

    /**
     * calculate multiplication.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) throws Exception {
        return first.calculate(dictVars) * second.calculate(dictVars);
    }
}
