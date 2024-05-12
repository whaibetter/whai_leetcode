package cn.whaifree.redo.redo.redo_24_4_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 17:08
 * @注释
 */
public class LeetCode474 {

    @Test
    public void test() {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(new Solution().findMaxForm(strs, m, n));
    }

    class Solution {
        public int findMaxForm(String[] strs, int m, int n) {

            /**
             * dp[i][j][k] 表示包含 i个0，j个1，从前k个子集中取出的最大子集长度
             *
             * i j 为背包容量
             * k为物品
             *
             * for 背包
             *      统计0，1数量
             *      fori
             *          forj
             *              if(i>=zero j>=one)
             *                  dp[i][j][k] = max(dp[i][j][k-1],dp[i-zero][j-one][k-1]+1)
             *              else
             *                  dp[i][j][k] = dp[i][j][k-1]
             *
             */


            int[][][] dp = new int[m + 1][n + 1][strs.length + 1];

            for (int k = 1; k <= strs.length; k++) {
                String str = strs[k-1];
                int zero = calculateZeroNumber(str);
                int one = str.length() - zero;
                for (int i = 0; i <= m; i++) {
                    for (int j = 0; j <= n; j++) {
                        if (i >= zero && j >= one) {
                            dp[i][j][k] = Math.max(dp[i][j][k - 1], dp[i - zero][j - one][k - 1] + 1);
                        } else {
                            dp[i][j][k] = dp[i][j][k - 1];
                        }
                    }
                }
            }

            return dp[m][n][strs.length];
        }

        public int calculateZeroNumber(String str) {
            int num = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    num++;
                }
            }
            return num;
        }


    }
}
