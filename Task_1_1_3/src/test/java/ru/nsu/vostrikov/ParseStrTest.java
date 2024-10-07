package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParseStrTest {
    @Test
    void testParseWithSpaces() throws Exception {
        String str = "((((4 + 5) + (6/2))   + (x*2)) - (  x + 4))";
        ParseStr parser = new ParseStr();
        Expression exp = parser.parseStr(str);
        assertEquals(9.0, exp.eval("x=1"));
        assertEquals("((((4+5)+(6/2))+(x*2))-(x+4))", exp.toString());
    }
}