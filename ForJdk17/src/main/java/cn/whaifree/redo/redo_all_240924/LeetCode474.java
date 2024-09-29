package cn.whaifree.redo.redo_all_240924;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 12:33
 * @注释
 */
public class LeetCode474 {

    class Solution {
        /**
         * 物品 strs
         * 背包0
         * 背包1
         *
         * @param strs
         * @param m
         * @param n
         * @return
         */
        public int findMaxForm(String[] strs, int m, int n) {
            int[][][] dp = new int[strs.length][m + 1][n + 1];

            String index0 = strs[0];
            int index0Zero = countZero(index0);
            int index0One = index0.length() - index0Zero;
            for (int i = index0Zero; i <= m; i++) {
                for (int j = index0One; j <= n; j++) {
                    dp[0][i][j] = 1;
                }
            }


            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                int countZero = countZero(str);
                int countOne = str.length() - countZero;
                for (int j = 1; j <= m; j++) {
                    for (int k = 1; k <= n; k++) {
                        if (countZero <= j && countOne <= k) {
                            // 放得下
                            // 去掉本单词的最大可能性+1
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - countZero][k - countOne] + 1);
                        } else {
                            dp[i][j][k] = dp[i - 1][j][k];
                        }
                    }
                }
            }
            return dp[strs.length - 1][m][n];
        }

        public int countZero(String str) {
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0')
                    count++;
            }
            return count;
        }
    }
}

class A {
}

class B extends A {
    public B() {
        super();
        System.out.println("ds");
    }
}
