package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 15:42
 * @注释
 */
public class LeetCode474 {

    @Test
    public void test() {
        String[] strs = {"10", "0001", "1", "0"};
        int m = 5;
        int n = 3;
        Solution solution = new Solution();
        int result = solution.findMaxForm(strs, m, n);
        System.out.println(result);
    }

    class Solution {
        /**
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
                String str = strs[i];
                int zeroCount = countZero(str);
                int oneCount = str.length() - zeroCount;
                for (int j = m; j >= 0; j--) {
                    for (int k = n; k >= 0; k--) {
                        if (j >= zeroCount && k >= oneCount) {
                            dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                        }
                    }
                }
            }
            return dp[m][n];
        }

        public int countZero(String str) {
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    count++;
                }
            }
            return count;
        }
    }

}
