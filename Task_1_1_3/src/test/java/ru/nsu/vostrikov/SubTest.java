package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class SubTest {
    @Test
    void testToString() {
        Sub sub = new Sub(new Number(54), new Number(72));
        assertEquals("(54-72)", sub.toString());
    }

    @Test
    void testCalculate() {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 34.0);
        Sub sub = new Sub(new Number(12), new Variable("x"));
        assertEquals(-22.0, sub.calculate(dict));
    }

    @Test
    void testDerivative() {
        Sub sub = new Sub(new Variable("x"), new Number(1234));
        Expression res = sub.derivative("x");
        assertEquals("(1-0)", res.toString());
    }

    @Test
    void testZeroCase() {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 0.0);
        Sub sub = new Sub(new Variable("x"), new Number(12));
        assertEquals(-12.0, sub.calculate(dict));

    }
}