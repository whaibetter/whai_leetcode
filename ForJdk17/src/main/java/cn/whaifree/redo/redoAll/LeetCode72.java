package cn.whaifree.redo.redoAll;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 20:59
 * @注释
 */
public class LeetCode72 {

    class Solution {

        /**
         * 对应位置字符相同，取左上角的数字为操作数，因为这个数已经对上了
         *
         * - 删除 dp[i-1][j]+1
         * - 替换 dp[i-1][j-1]+1
         * - 插入 dp[i][j-1]+1
         *
         * dp[i][j]表示把0-i变为0-j的最少操作次数
         *       h o r s e
         *     0 1 2 3 4 5
         *   0 0 1 2 3 4 5
         * r 1 1 1 2 2 3 4   a b
         * o 2 1 2 1 2 3 4   c d  d的数量由前面acd已经计算过的最小操作得出，即d这个操作只需要你的acb+1次操作必然能变成你要的样子
         * s 3 1 3 2 3 2 3
         *
         *
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
                        dp[i][j] = Math.min(
                                dp[i - 1][j - 1] + 1, // 替换 [i-1][j-1]
                                Math.min(
                                        dp[i - 1][j] + 1, // 增加 [i][j-1]
                                        dp[i][j - 1] + 1) // 删除 [i-1][j]
                        );
                    }
                }
            }

            return dp[len1][len2];
        }
    }
}
