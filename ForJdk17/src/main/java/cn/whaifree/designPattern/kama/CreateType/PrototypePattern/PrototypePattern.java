package cn.whaifree.designPattern.kama.CreateType.PrototypePattern;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 18:35
 * @注释
 */
public class PrototypePattern {

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.next();
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        int num = scanner.nextInt();
        Shape shape = new Shape(color, width, height);


        for (int i = 0; i < num; i++) {
            System.out.println(shape.clone());
        }
    }
}


class Shape implements Cloneable{
    String color;
    int width;
    int height;

    @Override
    public String toString() {
        // Color: Red, Width: 10, Height: 5
        return "Color: " + color + ", Width: " + width + ", Height: " + height;
    }

    public Shape(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    protected Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }
}
