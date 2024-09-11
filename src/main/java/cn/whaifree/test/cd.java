package cn.whaifree.test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/7 12:44
 * @注释
 */
public enum cd {
    A(1, "@"),
    ;
    int age;
    String name;

    cd(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(cd.values()));
        System.out.println(cd.valueOf("A"));

    }
}
