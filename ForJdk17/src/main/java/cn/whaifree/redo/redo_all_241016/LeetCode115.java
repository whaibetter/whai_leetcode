package cn.whaifree.redo.redo_all_241016;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 12:00
 * @注释
 */
public class LeetCode115 {

    class Solution {
        /**
         *
         * dp[i][j] 表示s的 （以i-1为结尾的字符串）（的子序列） 在t的0-j中出现的个数
         *
         * - 用本格
         * - 不用本格
         *
         *   ‘’ b a g
         * ‘’ 1 0 0 0
         *  b 1 1 0 0
         *  a 1 1 1 0
         *  b 1 2 1 0
         *  g 1 2 1 1
         *  b 1 3 1 1
         *  a 1 3 4 1         用这个的a，使用dp[i-1][j-1] 表示前面所有匹配的次数；  不用这个a，使用已经匹配好的次数 dp[i-1][j]
         *  g 1 3 4 5
         *
         * @param s
         * @param t
         * @return
         */
        public int numDistinct(String s, String t) {

            int[][] dp = new int[s.length() + 1][t.length() + 1];

            for (int i = 0; i <= s.length(); i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= t.length(); j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        // 之前已经匹配的个数 dp[i - 1][j]
                        // 前面都匹配，还差这个没匹配 dp[i - 1][j - 1]
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[s.length()][t.length()];
        }
    }

}
