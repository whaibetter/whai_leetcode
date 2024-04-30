package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/29 11:48
 * @注释
 */
public class LeetCode72 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int c = this.hashCode();
        // 输出二进制
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(c >>> 16));
        System.out.println(Integer.toBinaryString(c & (c >>> 16)));
        int i = solution.minDistance("ros", "horse");
        System.out.println(i);
    }


    class Solution {
        /**
         * dp[i][j] 表示word1的0-i变为word2的0-j至少需要的操作数
         *    ''  h o r s e
         * ''  0  1 2 3 4 5
         * r   1  1 2 2 3 4
         * o   2  2 1 2 3 4
         * s   3  3 2 2 3 3
         *
         *
         * 相同
         * [i-1][j-1]
         *
         * 不相同
         * - 替换 [i-1][j-1] + 1
         * - 增加 [i][j-1] + 1
         * - 删除 [i-1][j] + 1
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 0; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= len2; i++) {
                dp[0][i] = i;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] =
                                Math.min(
                                        dp[i - 1][j - 1] + 1, // 替换 [i-1][j-1]
                                        Math.min(
                                                dp[i - 1][j] + 1,  // 增加 [i][j-1]
                                                dp[i][j - 1] + 1)  // 删除 [i-1][j]
                                );
                    }
                }
            }

            return dp[len1][len2];
        }
    }
}
