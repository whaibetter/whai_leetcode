package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/26 11:44
 * @注释
 */
public class LeetCode115 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct("rabbbit", "rabbit"));
    }

    class Solution {
        public int numDistinct(String s, String t) {
            /**
             * dp[i][j] 以i-1为 结尾的 s子序列 中出现以j-1为 结尾的 t的个数为dp[i][j]
             *
             *    '' b a g
             * '' 1  0 0 0
             *  b 1  1 0 0
             *  a 1  1 1 0
             *  e 1  1 1 0
             *  g 1  1 1 1
             *  g 1  1 1 2
             *
             * if s[i]==t[j]
             *      1. 用s[i - 1]来匹配 dp[i - 1][j - 1]   bagg和bag t匹配到s的第二个g时，使用第一个g
             *      2. 不用第s[i - 1]来匹配 dp[i - 1][j] bagg和bag t匹配到s的第二个g时，不使用第一个g
             *      dp[i][j] = dp[i-1][j-1]+dp[i - 1][j];
             * else
             *      不用s[i - 1]来匹配，模拟s中删除了这个元素
             *      dp[i][j] = dp[i - 1][j];
             *
             */

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
