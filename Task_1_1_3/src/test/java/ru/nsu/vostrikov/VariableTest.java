package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class VariableTest {
    @Test
    void testToString() {
        Variable var = new Variable("xxxxx");
        assertEquals("xxxxx", var.toString());
    }

    @Test
    void testDerivative() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        assertEquals("1", x.derivative("x").toString());
        assertEquals("0", x.derivative("y").toString());
        assertEquals("1", y.derivative("y").toString());
        assertEquals("0", y.derivative("x").toString());
    }

    @Test
    void testCalculate() throws Exception {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 56.0);
        dict.put("y", 7.0);
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        assertEquals(56.0, x.calculate(dict));
        assertEquals(7.0, y.calculate(dict));
    }

    @Test
    void testNoVariable() throws Exception {
        Variable var = new Variable("x");
        Exception myException = new Exception("x have no value");
        try {
            var.eval("y=5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), myException.getMessage());
        }
    }
}