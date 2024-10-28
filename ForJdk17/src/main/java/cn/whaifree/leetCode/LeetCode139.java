package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/28 13:15
 * @注释
 */
public class LeetCode139 {

    @Test
    public void test() {
//        System.out.println(new Solution().wordBreak("leetcode", List.of("leet", "code")));
//        System.out.println(new Solution().wordBreak("leetcode", List.of("leet", "cod", "e")));
        // ["apple","pen"]
        System.out.println(new Solution().wordBreak("applepenapple", List.of("apple", "pen")));

    }

    class Solution {

        /**
         *
         * dp[i][j] 表示s中前j个字母能否被0-i中任意元素组合出来
         *
         * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
         *
         *       c a t s a n d o g
         * cats  0 0 0 1 0
         * dog
         * sand
         * and
         * cat
         *      '' l e e t c o d e
         *  ''  1  0 0 0 0 0 0 0 0
         * leet 1  0 0 0 1 0 0 0 0
         * code 1  0 0 0 0 0 0 0 1
         *
         *         a p p l e p e n a p p l e
         * apple 1 0 0 0 0 1 0 0 0 0 0 0 0 1
         * pen   1 0 0 0 0 1 0 0 1 0 0 0 0 0
         *
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            // 完全背包，先便利字符串再遍历背包，确保重会重复选择背包
            int len = s.length();
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;
            for (int j = 1; j <= len; j++) {
                for (int i = 0; i < wordDict.size(); i++) {
                    String str = wordDict.get(i);
                    int index = j - str.length();
                    if (index >= 0 && dp[index] && check(s, j - 1, str)) {
                        dp[j] = true;
                    }
                }
            }
            return dp[len];
        }



        public boolean check(String s, int start, String word) {
            for (int i = word.length() - 1; i >= 0; i--) {
                if (word.charAt(i) != s.charAt(start)) {
                    return false;
                }
                start--;
            }
            return true;
        }
    }
    @Test
    public  void main() {
        System.out.println(new Solution().check("leetcode", 7, "code"));
    }
}
