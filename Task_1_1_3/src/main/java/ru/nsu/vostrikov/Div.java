package ru.nsu.vostrikov;

import java.util.HashMap;

class Div extends Expression {
    private final Expression numerator;
    private final Expression denominator;

    public Div(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        return "(" + numerator.toString() + "/" + denominator.toString() + ")";
    }

    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(numerator.derivative(variable), denominator), new Mul(numerator, denominator.derivative(variable))), new Mul(denominator, denominator));
    }

    public int calculate(HashMap<String, Integer> dictVars) {
        return numerator.calculate(dictVars) / denominator.calculate(dictVars);
    }
}
