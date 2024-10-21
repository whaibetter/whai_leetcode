package cn.whaifree.tech.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 23:24
 * @注释
 */
public class staticClass {

    static int i = 1;
    static {
        System.out.println("A " + i++);
    }
    public static void main(String[] args) {
        System.out.println("N");
    }
    static {
        System.out.println("B " + i++);
    }
}
