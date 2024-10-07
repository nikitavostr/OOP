package ru.nsu.vostrikov;

import java.util.HashMap;

/**
 * the Main class.
 */
public class Main {

    /**
     * the main function.
     */
    public static void main(String[] args) {
        Variable var1 = new Variable("xxxx");
        Variable var2 = new Variable("y");
        Expression exp = new Add(new Mul(var1, var1), new Mul(var2, var2));
        exp.print();
        Expression der1 = exp.derivative("xxxx");
        der1.print();
        Expression der2 = exp.derivative("y");
        der2.print();
        String str = "((5 + x) * x)";
        ParseStr parser = new ParseStr();
        String input = "((x+2) + 7)";
        Expression exp2 = parser.parseStr(input);
        System.out.println(exp2.toString());

    }
}