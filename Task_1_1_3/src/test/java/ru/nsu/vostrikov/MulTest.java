package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class MulTest {
    @Test
    void testToString() {
        Mul mul = new Mul(new Number(9), new Number(7));
        assertEquals("(9*7)", mul.toString());
    }

    @Test
    void testDerivative() {
        Variable var = new Variable("x");
        Mul mul = new Mul(new Mul(var, var), new Number(5));
        assertEquals("((((1*x)+(x*1))*5)+((x*x)*0))",
                mul.derivative("x").toString());
    }

    @Test
    void testMulByZero() {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 0.0);
        Mul mul = new Mul(new Variable("x"), new Number(7));
        assertEquals(0.0, mul.calculate(dict));
    }

    @Test
    void testCalculateDerivative() {
        Variable var = new Variable("x");
        Expression mul = new Mul(new Mul(var, var), new Number(5));
        Expression der = mul.derivative("x");
        assertEquals(20.0, der.eval("x=2"));
    }

    @Test
    void testSimpleCase() {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", 12.0);
        dict.put("y", -7.0);
        Mul mul = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(-84.0, mul.calculate(dict));
    }
}