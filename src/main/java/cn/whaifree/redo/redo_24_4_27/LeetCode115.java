package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/30 11:20
 * @注释
 */
public class LeetCode115 {
    @Test
    public void test() {
        String s = "babgbag";
        String t = "bag";
        System.out.println(new Solution().numDistinct(s, t));
    }



    class Solution {
        /**
         * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
         * @param s
         * @param t
         * @return
         */
        public int numDistinct(String s, String t) {
            /**
             *
             * dp[i][j] 表示0-j在0-i子序列中出现的次数
             *    '' b a b g b a g
             *''  1  1 1 1 1 1 1 1
             * b  0  1 1 2 2 3 3 3
             * a  0  0 1 1 1 1 4 4
             * g  0  0 0 0 1 1 1 5
             *
             * if s[i] == t[j]
             *      dp[i][j] =
             *           +
             *           - s使用i-1个位置 dp[i-1][j-1]
             *           - s不使用i-1个位置 dp[i][j-1]
             * else
             *      dp[i][j-1]
             */

            int lenS = s.length();
            int lenT = t.length();
            int[][] dp = new int[lenT + 1][lenS + 1];
            for (int i = 0; i <= lenS; i++) {
                dp[0][i] = 1;
            }
            for (int j = 1; j <= lenT; j++) {
                for (int i = 1; i <= lenS; i++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[j][i] = dp[j - 1][i - 1] + dp[j][i - 1];
                    } else {
                        dp[j][i] = dp[j][i - 1];
                    }
                }
            }
            return dp[lenT][lenS];
        }
    }
}
