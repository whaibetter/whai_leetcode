package cn.whaifree.redo.redo_all_241121;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/30 12:42
 * @注释
 */
public class LeetCode474 {

    class Solution {
        /**
         * 从strs中任意取，放入i，j个的背包中的最大子集长度
         *
         *  背包是二维的
         *
         * 从str 0-i中任意取，满足j个0和k个1的最大长度
         *
         * @param strs
         * @param m
         * @param n
         * @return
         */
        public int findMaxForm(String[] strs, int m, int n) {

            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i < strs.length; i++) {
                String item = strs[i];
                int zeroCount = countZero(item);
                int oneCount = item.length() - zeroCount;
                // 放入m，n的背包中
                for (int j = m; j >= 0; j--) {
                    for (int k = n; k >= 0; k--) {
                        if (j >= zeroCount && k >= oneCount) {
                            // 背包放得下
                            dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                        }
                    }
                }
            }
            return dp[m][n];
        }

        public int countZero(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    count++;
                }
            }
            return count;
        }
    }

}
