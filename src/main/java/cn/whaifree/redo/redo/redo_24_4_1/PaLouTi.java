package cn.whaifree.redo.redo.redo_24_4_1;

import org.junit.Test;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 11:46
 * @注释
 */
public class PaLouTi {

    @Test
    public void test() {
        int capacity = 3;
        int m = 2;
        System.out.println(plt(capacity, m));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int capacity = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(plt(capacity, m));

    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * n=3 m=2
     * 3
     * 当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。
     * @param capacity
     * @param m
     * @return
     */
    public static int plt(int capacity, int m) {
        /**
         * 完全背包
         *
         * m为物品的重量，背包容量为n
         *
         * dp[j] 表示从1-m个选择中任意选择，爬到j台阶可以有的方法数
         *
         * dp[j] = dp[j]+dp[j-i]
         *
         */
        int[] dp = new int[capacity + 1];
        dp[0] = 1;
        for (int j = 1; j <= capacity; j++) {
            for (int i = 1; i <= m; i++) {
                if (i <= j) {
                    dp[j] = dp[j] + dp[j - i];
                }
            }
        }
        return dp[capacity];
    }
}
