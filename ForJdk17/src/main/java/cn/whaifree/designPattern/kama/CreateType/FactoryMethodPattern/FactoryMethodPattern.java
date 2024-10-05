package cn.whaifree.designPattern.kama.CreateType.FactoryMethodPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * 	工厂方法模式
 *
 * 工厂方法模式
 * 单一产品：工厂方法模式主要用于创建单一类型的产品。它定义了一个创建对象的接口，但允许子类决定实例化哪一个类。
 * 单一责任原则：工厂方法模式中的工厂类只负责创建一种产品。
 *
 * 扩展性：当需要增加新的产品时，需要新增具体的工厂类。
 * 代码结构：
 * 定义一个创建产品的接口。
 * 具体工厂实现这个接口来创建对应的具体产品。
 *
 *
 * 抽象工厂模式
 * 产品族：抽象工厂模式用于创建一组相关或依赖的对象（即产品族），而无需指定它们具体的类。
 * 多产品：一个工厂可以创建多个相关的对象。
 * 扩展性：当需要增加新的产品族时，需要新增具体的工厂类；但当需要增加新的产品种类时，不需要修改现有的工厂类。
 * 代码结构：
 * 定义一个创建一系列相关产品的接口。
 * 具体工厂实现这个接口来创建对应的一系列具体产品。
 *
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 16:28
 * @注释
 */
public class FactoryMethodPattern {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        for (int i1 = 0; i1 < i; i1++) {
            String next = scanner.next();
            int j = scanner.nextInt();
//            for (int i2 = 0; i2 < j; i2++) {
//                Context.getFactory(next).produce();
//            }
            for (int k = 0; k < j; k++) {
                FactoryContext.getiFactory(next).produce();
            }
        }
    }

}

enum FactoryContext {
    Circle("Circle", new CircleFactory()),
    Square("Square", new SquareFactory()),
    ;

    String name;
    IFactory iFactory;

    FactoryContext(String name, IFactory iFactory) {
        this.name = name;
        this.iFactory = iFactory;
    }

    public static IFactory getiFactory(String key) {
        for (FactoryContext value : values()) {
            if (value.name.equals(key)) {
                return value.iFactory;
            }
        }
        return null;
    }
}

class Context{
    static Map<String, IFactory> map = new HashMap<>();
    static {
        map.put("Circle", new CircleFactory());
        map.put("Square", new SquareFactory());
    }

    public static IFactory getFactory(String key){
        if (map.containsKey(key)) {
            return map.get(key);
        }

        return new IFactory() {
            @Override
            public Shape produce() {
                System.out.println("No Factory");
                return null;
            }
        };
    }
}

class Shape{

}

class Circle extends Shape{

}
class Square extends Shape{

}

interface IFactory{
    Shape produce();
}

class CircleFactory implements IFactory{

    @Override
    public Shape produce() {
        System.out.println("Circle Block");
        return new Circle();
    }
}

class SquareFactory implements IFactory{

    @Override
    public Shape produce() {
        System.out.println("Square Block");
        return new Square();
    }
}
