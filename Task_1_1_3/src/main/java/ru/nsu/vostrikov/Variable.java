package ru.nsu.vostrikov;

import java.util.HashMap;

class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public Expression derivative(String variable) {
        return new Number(name.equals(variable) ? 1 : 0);
    }

    public int calculate(HashMap<String, Integer> dictVars) {
        return dictVars.getOrDefault(name, 0);
    }
}
