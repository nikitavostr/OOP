package ru.nsu.vostrikov;

import java.util.HashMap;

class Mul extends Expression {
    private final Expression first;
    private final Expression second;

    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first.toString() + "*" + second.toString() + ")";
    }

    public Expression derivative(String variable) {
        return new Add(new Mul(first.derivative(variable), second),
                new Mul(first, second.derivative(variable)));
    }

    public int calculate(HashMap<String, Integer> dictVars) {
        return first.calculate(dictVars) * second.calculate(dictVars);
    }
}
