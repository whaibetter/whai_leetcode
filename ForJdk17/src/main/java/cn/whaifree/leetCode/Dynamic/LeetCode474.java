package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/26 11:49
 * @注释
 */
public class LeetCode474 {

    @Test
    public void test()
    {
        String[] strs = {"10","0001","111001","1","0"};
        int m = 5;
        int n = 3;
//        System.out.println(new Solution().findMaxForm(strs, m, n));
        // "10", "0", "1"
        strs = new String[]{"10","0","1"};
        System.out.println(new Solution1().findMaxForm(strs, 1, 1));

    }
    class Solution {
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

    class Solution1 {
        public int findMaxForm(String[] strs, int m, int n) {

            // 二维dp dp[j][k] 表示 从字符串[1-i]中取字符串放入 0背包容量为j 1背包容量为k 的最大子串长度
            //
            // 递推公式
            // 遍历每个字符串，获取0个数为zeroNumber 1 个数为oneNumber
            // dp[j][k] =
            //          - 放   dp[j-zero][k-one]+1
            //          - 不放  dp[j][k]
            // 遍历 背包
            //      - 从后往前 确保背包容量j能够放下oneNumber 即j>=zeroNumber
            int[][] dp = new int[m + 1][n + 1];

            for (String str : strs) {
                int zeroNumber = calculateZeroNumber(str);
                int oneNumber = str.length() - zeroNumber;
                for (int j = m; j >= zeroNumber; j--) {
                    for (int k = n; k >= oneNumber; k--) {
                        dp[j][k] = Math.max(dp[j - zeroNumber][k - oneNumber] + 1, dp[j][k]);
                    }
                }
            }

            return dp[m][n];
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
