package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/27 12:45
 * @注释
 */
public class KaMa47 {

    @Test
    public void test()
    {

        int i = new Solution().plt(3, 2);
        System.out.println(i);
    }

    /* https://kamacoder.com/problempage.php?pid=1067
     */
    class Solution{

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int capacity = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(plt(capacity, m));
        }

        /**
         * @param capacity 需要n阶
         * @param m 每一步可以走几个
         * @return
         */
        public static int plt(int capacity, int m) {

            // 排列
            int[] dp = new int[capacity + 1];

            dp[0] = 1;
            for (int j = 0; j <= capacity; j++) {
                for (int i = 1; i <= m; i++) {
                    if (j >= i) {
                        dp[j] += dp[j - i];
                    }
                }
            }
            return dp[capacity];
        }

    }
}
