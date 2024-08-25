package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @Author whai文海
 * @Date 2024/8/22 21:00
 * @version 1.0
 * @注释
 *
 */
public class LeetCode474 {

    @Test
    public void test() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        Solution solution = new Solution();
        System.out.println(solution.findMaxForm(strs, m, n));
    }

    class Solution {
        /**
         *
         * 物品  ["10", "0001", "111001", "1", "0"]
         *
         * 背包
         *
         * dp[i][j][k] 表示在前i个字符串中，放入了j个0和k个1的背包最大子集长度
         * 物品 i
         * 0 j
         * 1 k
         *
         *
         *
         *
         *
         *
         *
         * @param strs
         * @param m
         * @param n
         * @return
         */
        public int findMaxForm(String[] strs, int m, int n) {

            int[][][] dp = new int[strs.length][m + 1][n + 1];

            String index0 = strs[0];
            int index0Zero = calculateZero(index0);
            int index0One = index0.length() - index0Zero;
            for (int i = index0Zero; i <= m; i++) {
                for (int j = index0One; j <= n; j++) {
                    dp[0][i][j] = 1;
                }
            }


            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                int zero = calculateZero(str);
                int one = str.length() - zero;
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        if (zero > j || one > k) {
                            dp[i][j][k] = dp[i - 1][j][k];
                        }else {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - one] + 1);
                        }
                    }
                }
            }
            return dp[strs.length-1][m][n];
        }

        public int calculateZero(String str) {
            int zero = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zero++;
                }
            }
            return zero;
        }
    }

    class Solution1 {
        public int findMaxForm(String[] strs, int m, int n) {

            // 三维 dp[i][j][k] 表示在前i个字符串中，包含了了j个0和k个1的最大子集长度
            // String[i]为物品  j k 为背包
            // 初始化： 当物品i=0 ，表示在前0个字符串中，没有字符串可以使用，dp[0][j][k]=0
            // 递推公式：
            //          获取0的数量numZero 获取1的数量numOne
            //    当j<numZero 或 k<numOne时，dp[i][j][k]=dp[i-1][j][k] 放不下
            //    else
            //          - 放   dp[i-1][j-numZero][k-numOne]+1
            //          - 不放 dp[i-1][j][k]

            int length = strs.length;
            int[][][] dp = new int[length + 1][m + 1][n + 1];
            for (int i = 1; i < strs.length + 1; i++) {
                String str = strs[i-1];
                int zeroNumber = calculateZeroNumber(str);
                int oneNumber = str.length() - zeroNumber;
                for (int j = 0; j <= m; j++) {
                    for (int k = 0; k <= n; k++) {
                        if (j <  zeroNumber || k <  oneNumber) {
                            dp[i][j][k] = dp[i - 1][j][k];
                        } else {
                            dp[i][j][k] = Math.max(dp[i - 1][j - zeroNumber][k - oneNumber] + 1, dp[i - 1][j][k]);
                        }
                    }
                }
            }
            return dp[length][m][n];
        }

        public int calculateZeroNumber(String str)
        {
            int num = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0')
                {
                    num++;
                }
            }
            return num;
        }
    }

}
