package cn.whaifree.designPattern.kama.StructureType.DecoratorPattern;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 16:43
 * @注释
 */
public class DecoratorPattern {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == 1) {
                Coffee blackCoffee = new BlackCoffee();
                CoffeeDecorator coffeeDecorator = new CoffeeDecorator(blackCoffee);
                coffeeDecorator.decorate(blackCoffee, b);
            } else {
                Coffee latte = new Latte();
                CoffeeDecorator coffeeDecorator = new CoffeeDecorator(latte);
                coffeeDecorator.decorate(latte, b);
            }
        }
    }
}

interface Coffee {
    void addCoffeeBeans();
}

class BlackCoffee implements Coffee {

    @Override
    public void addCoffeeBeans() {
        System.out.println("Brewing Black Coffee");
    }
}

class Latte implements Coffee {

    @Override
    public void addCoffeeBeans() {
        System.out.println("Brewing Latte");
    }
}

class CoffeeDecorator {

    private final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public Coffee decorate(Coffee coffee, int add) {
        if (add == 1) {
            addMilk(coffee);
        } else {
            addSugar(coffee);
        }
        return coffee;
    }

    public void addMilk(Coffee coffee) {
        coffee.addCoffeeBeans();
        System.out.println("Adding Milk");
    }

    public void addSugar(Coffee coffee) {
        coffee.addCoffeeBeans();
        System.out.println("Adding Sugar");
    }

}

