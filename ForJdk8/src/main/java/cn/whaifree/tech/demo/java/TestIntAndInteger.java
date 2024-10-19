package cn.whaifree.tech.demo.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 22:49
 * @注释
 */
public class TestIntAndInteger {
    public static void main(String[] args) {
        Integer a = new Integer(100);
        Integer b = new Integer(100);
        System.out.println(a == b);
        Integer c = new Integer(100);
        int d = 100;
        System.out.println(c == d);
        Integer e = new Integer(100);
        Integer f = 100;
        System.out.println(e == f);
        Integer g = 100;
        Integer h = 100;
        System.out.println(g == h);
        Integer j = 128;
        Integer i = 128;
        System.out.println(i == j);
        Integer k = 1;
        int l = 1;
        System.out.println(k == l);
    }
}
