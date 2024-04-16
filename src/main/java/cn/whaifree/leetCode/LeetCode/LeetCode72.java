package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/16 11:45
 * @注释
 */
public class LeetCode72 {

    @Test
    public void test()
    {
        String word1 = "horse";
        String word2 = "ros";
        int res = new Solution().minDistance(word1, word2);
        System.out.println(res);
    }

    class Solution {
        public int minDistance(String word1, String word2) {
            // dp[i][j] 表示将word1的第i个位置字符 替换为 word2的第j个字符所需的最小操作数
            //      - 删除 dp[i-1][j]
            //      - 增加 dp[i][j-1]
            //      - 替换 dp[i-1][j-1]
            // dp[i][j] = min +1
            int wL1 = word1.length();
            int wL2 = word2.length();
            int[][] dp = new int[wL1 + 1][wL2 + 1];
            for (int i = 0; i <= wL1; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= wL2; i++) {
                dp[0][i] = i;
            }

            for (int i = 1; i <= wL1; i++) {
                for (int j = 1; j <= wL2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                        // 如果对应位置两个字母相等，不用做任何操作，直接使用dp[i - 1][j - 1]
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
            }
            return dp[wL1][wL2];
        }
    }

}
