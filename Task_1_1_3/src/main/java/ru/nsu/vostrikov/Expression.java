package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Base class.
 */
abstract class Expression {

    /**
     * output.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * eval with variable.
     */
    public double eval(String string) {
        return calculate(parseVariables(string));
    }

    /**
     * parse.
     */
    private HashMap<String, Double> parseVariables(String input) {
        HashMap<String, Double> variables = new HashMap<>();
        String[] pairs = input.split(";");
        for (String pair : pairs) {
            String[] elements = pair.split("=");
            variables.put(elements[0], Double.parseDouble(elements[1]));
        }
        return variables;
    }

    /**
     * calculate with dict of variables.
     */
    protected abstract Double calculate(HashMap<String, Double> dictVars);

    /**
     * derivative.
     */
    public abstract Expression derivative(String variable);
}
