package ru.nsu.vostrikov;

import java.util.HashMap;

class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public int calculate(HashMap<String, Integer> dictVars) {
        return value;
    }

    public Expression derivative(String variable) {
        return new Number(0);
    }
}
