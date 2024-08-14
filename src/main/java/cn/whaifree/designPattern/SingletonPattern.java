package cn.whaifree.designPattern;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class SingletonPattern {

    /**
     * https://kamacoder.com/problempage.php?pid=1074
     *
     * 【设计模式专题之单例模式】1.小明的购物车
     * 题目描述
     * 小明去了一家大型商场，拿到了一个购物车，并开始购物。请你设计一个购物车管理器，记录商品添加到购物车的信息（商品名称和购买数量），并在购买结束后打印出商品清单。（在整个购物过程中，小明只有一个购物车实例存在）。
     * 输入描述
     * 输入包含若干行，每行包含两部分信息，分别是商品名称和购买数量。商品名称和购买数量之间用空格隔开。
     * 输出描述
     * 输出包含小明购物车中的所有商品及其购买数量。每行输出一种商品的信息，格式为 "商品名称 购买数量"。
     * 输入示例
     * Apple 3
     * Banana 2
     * Orange 5
     * 输出示例
     * Apple 3
     * Banana 2
     * Orange 5
     * 提示信息
     * 本道题目请使用单例设计模式：
     *
     * 使用私有静态变量来保存购物车实例。
     *
     * 使用私有构造函数防止外部直接实例化。
     */

    static class ManagerCar{

        private HashMap<String, Integer> carHas = null;

        public ManagerCar() {
            this.carHas = new HashMap<>();
        }

        public HashMap<String, Integer> getCarHas() {
            return carHas;
        }

        private volatile static ManagerCar managerCar;

        public static ManagerCar getManagerCar() {
            if (managerCar == null) {
                synchronized (ManagerCar.class) {
                    if (managerCar == null) {
                        managerCar = new ManagerCar();
                    }
                }
            }
            return managerCar;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> carHas = ManagerCar.getManagerCar().getCarHas();


        while (true) {
            String nextLine = scanner.nextLine();
            if ("stop".equals(nextLine)) {
                break;
            }
            String[] s = nextLine.split(" ");
            String obj = s[0];
            Integer num = Integer.valueOf(s[1]);
            carHas.put(obj, carHas.getOrDefault(obj, 0) + num);
            /**
             * 添加商品到购物车
             * public void addItem(String name, int quantity) {
             *     items.merge(name, quantity, Integer::sum);
             *
             */
        }

        carHas.forEach(
                (s, integer) -> System.out.println(s + " " + integer)
        );

        scanner.close();

    }


}
