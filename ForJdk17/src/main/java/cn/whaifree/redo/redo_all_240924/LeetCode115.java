package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 10:23
 * @注释
 */
public class LeetCode115 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int i = solution.numDistinct("babgbag", "bag");
        System.out.println(i);
        // rabbit
        int i1 = solution.numDistinct("rabbbit", "rabbit");
        System.out.println(i1);
    }

    class Solution {
        /**
         *
         *
         *   '' b a g
         *   1  0 0 0
         * b 1  1 0 0
         * a 1  1 1 0
         * b 1  2 1 0  一样： 之前的子序列 dp[i-1][j-1] + s其他前缀在t中出现的 dp[i-1][j]
         * g
         * a
         * g
         *
         *  '' b a b g b a g
         *'' 1 1 1 1 1 1 1 1
         * b 0 1 1 2 2 3 3 3
         * a 0 0 1 1 1 1 4 4
         * g 0 0 0 0 1 1 1 5
         * 一样：用左上角+左 表示i-1 j 中匹配的次数（这个char i用的是前面的，不用第s[i - 1]来匹配） + 本次i-1 j-1 （使用i-1进行匹配）就差这个i匹配的
         * else ： 用左
         *
         *   '' b a b g b a g
         *''  0 0 0 0 0 0 0 0
         * b  0 1 1 2 2 3 3 3
         * a  0 0 1 1 1 1 4 4
         * g  0 0 0 0 1 1 1 5
         *
         * @param s
         * @param t
         * @return
         */
        public int numDistinct(String s, String t) {

            char[] sChar = s.toCharArray();
            char[] tChar = t.toCharArray();
            int[][] dp = new int[sChar.length + 1][tChar.length + 1];
            for (int i = 0; i < sChar.length; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i <= sChar.length; i++) {
                for (int j = 1; j <= tChar.length; j++) {
                    if (sChar[i - 1] == tChar[j - 1]) {
                        // 如果相同
                        // 使用i-1进行匹配 dp[i - 1][j - 1] bagg和bag t匹配到s的第二个g时，使用第一个g
                        // 不用第s[i - 1]来匹配 dp[i - 1][j] bagg和bag t匹配到s的第二个g时，不使用第一个g
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        // 不用s[i - 1]来匹配
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[sChar.length][tChar.length];
        }
    }
}
