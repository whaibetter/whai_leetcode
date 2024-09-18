package cn.whaifree.interview;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 19:01
 * @注释
 */
public class Jinshan {


    /**
     * 带k头牛
     *
     *
     *  n中选择k头 n*m^k
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int res = 1;
        for (int k = 1; k <= n; k++) {
            long choice = Cfactorial(n, k);
            long tmp = (long) (choice * Math.pow(m, k));
            res += tmp;
        }
        System.out.println(res);

    }

    public static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    public static long Cfactorial(int n, int m) {
        long a = factorial(n);

        return (n >= m) ? (a / factorial(n - m)) / factorial(m) : 0;
    }
}
