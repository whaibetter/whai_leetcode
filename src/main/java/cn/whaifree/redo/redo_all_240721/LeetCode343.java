package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/10 21:34
 * @注释
 */
public class LeetCode343 {

    @Test
    public void test() {
        System.out.println(integerBreak(10));
    }

    /**
     * dp[i]表示i可以拆出来的最大值
     * <p>
     * dp[i] = 遍历0~i-1，找到dp[k]*dp[i-k]的最大值
     * <p>
     * 0 1 2 3 4 5 6 7
     * 1 1 2 4 6 9 12
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
//        if (n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int tmpMax = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                tmpMax = Math.max(tmpMax, dp[j] * (i - j)); // 7 可能拆成（2 2）3 即dp[4] * 7-4
                tmpMax = Math.max(tmpMax, j * (i - j)); // 4 可能拆成 2 2 即ij 22
            }
            dp[i] = tmpMax;
        }
        return dp[n];
    }

    static Object o = new Object();
    static int count = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (o) {
                    while (count % 3 != 1) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("a");
                    o.notifyAll();
                    count++;
                }
            }

        }).start();


        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (o) {
                    while (count % 3 != 0) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("b");
                    o.notifyAll();
                    count++;
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (o) {
                    while (count % 3 != 2) {

                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    System.out.println("c");
                    o.notifyAll();
                    count++;
                }
            }

        }).start();


    }
}
