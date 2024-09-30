package ru.nsu.vostrikov;

import java.util.HashMap;

abstract class Expression {
    public void print() {
        System.out.println(this);
    }

    public int eval(String string) {
        return calculate(parseVariables(string));
    }

    private HashMap<String, Integer> parseVariables(String input) {
        HashMap<String, Integer> variables = new HashMap<>();
        String[] pairs = input.split(";");
        for (String pair : pairs) {
            String[] elements = pair.split("=");
            variables.put(elements[0], Integer.parseInt(elements[1]));
        }
        return variables;
    }

    protected abstract int calculate(HashMap<String, Integer> dict);

    public abstract Expression derivative(String variable);
}
