package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class DivTest {
    @Test
    void testToString() {
        Div division = new Div(new Number(1488), new Number(3));
        assertEquals("(1488/3)", division.toString());
    }

    @Test
    void testDerivative() {
        Div division = new Div(new Variable("x"), new Number(6));
        Expression result = division.derivative("x");
        assertEquals("(((1*6)-(x*0))/(6*6))", result.toString());
    }

    @Test
    void testEvaluate() throws Exception {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("xx", 126.0);
        dict.put("y", 9.0);
        Div division = new Div(new Variable("xx"), new Variable("y"));
        double result = division.calculate(dict);
        assertEquals(14, result);
    }

    @Test
    void testDivisionByZero() throws Exception {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("xx", 126.0);
        dict.put("y", 0.0);
        Div division = new Div(new Variable("xx"), new Variable("y"));
        double result = division.calculate(dict);
        assertEquals((double) 126 / 0, result);
    }

    @Test
    void testSimpleCase() throws Exception {
        HashMap<String, Double> dict = new HashMap<>();
        dict.put("x", -228.0);
        dict.put("y", 4.0);
        Div division = new Div(new Variable("x"), new Variable("y"));
        double result = division.calculate(dict);
        assertEquals(-57.0, result);
    }
}