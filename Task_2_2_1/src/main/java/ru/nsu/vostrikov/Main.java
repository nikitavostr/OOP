package ru.nsu.vostrikov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException{
        int workTime = 20;
        int bakerySpeeds [] = {3, 2, 3};
        int courierCap [] = {4, 2, 3};
        Pizzeria pizzeria = new Pizzeria(3, 3, 10, bakerySpeeds, courierCap);
        pizzeria.start();
        long startTime = System.currentTimeMillis();
        int id = 1;
        while (System.currentTimeMillis() - startTime < workTime * 1000L) {
            pizzeria.placeOrder(new PizzaOrder(id));
            id++;
            Thread.sleep(500);
        }
        pizzeria.closePizzeria();
        System.out.println("Запривачено");
        while(pizzeria.working()) {
            Thread.sleep(500);
        }
        pizzeria.stop();
        System.out.println("закрыто");
    }
}