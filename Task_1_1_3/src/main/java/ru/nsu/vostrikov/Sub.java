package ru.nsu.vostrikov;

import java.util.HashMap;

class Sub extends Expression {
    private final Expression first;
    private final Expression second;

    public Sub(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first.toString() + "-" + second.toString() + ")";
    }

    public int calculate(HashMap<String, Integer> dictVars) {
        return first.calculate(dictVars) - second.calculate(dictVars);
    }

    public Expression derivative(String variable) {
        return new Sub(first.derivative(variable), second.derivative(variable));
    }

}
