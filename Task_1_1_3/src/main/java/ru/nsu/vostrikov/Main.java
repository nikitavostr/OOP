package ru.nsu.vostrikov;

public class Main {

    public static void main(String[] args) {
        Variable var1 = new Variable("xxxx");
        Variable var2 = new Variable("y");
        Expression exp = new Add(new Mul(var1, var1), new Mul(var2, var2));
        exp.print();
        Expression der1 = exp.derivative("xxxx");
        der1.print();
        Expression der2 = exp.derivative("y");
        der2.print();
        int res = exp.eval("xxxx=3;y=4");
        System.out.println(res);
    }
}