package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * Div class.
 */
class Div extends Expression {
    private final Expression numerator;
    private final Expression denominator;

    /**
     * constructor.
     */
    public Div(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * convert to string.
     */
    @Override
    public String toString() {
        return "(" + numerator.toString() + "/" + denominator.toString() + ")";
    }

    /**
     * find derivative.
     *
     * @param variable - variable of derivative.
     */
    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(
                new Mul(numerator.derivative(variable), denominator),
                new Mul(numerator, denominator.derivative(variable))
        ),
                new Mul(denominator, denominator)
        );

    }

    /**
     * calculate division.
     *
     * @param dictVars - dict of variables.
     */
    @Override
    public Double calculate(HashMap<String, Double> dictVars) throws Exception {
        return numerator.calculate(dictVars) / denominator.calculate(dictVars);
    }
}
