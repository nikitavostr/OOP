package ru.nsu.vostrikov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class AddTest {
    @Test
    void testCalculate() {
        Expression expr = new Add(new Number(3), new Number(5));
        assertEquals(8.0, expr.calculate(new HashMap<>()));
    }

    @Test
    void testToString() {
        Expression expr = new Add(new Number(3), new Number(5));
        assertEquals("(3+5)", expr.toString());
    }

    @Test
    void testDerivative() {
        Variable x = new Variable("x");
        Expression expr = new Add(x, new Number(3));
        assertEquals("(1+0)", expr.derivative("x").toString());
    }

    @Test
    void testEvaluate() {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 6.0);
        Expression left = new Variable("x");
        Expression right = new Number(9);
        Add addition = new Add(left, right);
        double result = addition.calculate(dict);
        assertEquals(15, result);
    }
}