package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 15:08
 * @注释
 */
public class LeetCode139 {

    @Test
    public void test() {
        String s = "applepenapple";
        List<String> wordDict = List.of("apple", "pen");
        Solution solution = new Solution();
        boolean result = solution.wordBreak(s, wordDict);
        System.out.println(result);
    }


    class Solution {
        /**
         *
         * 无穷背包
         *
         *  从WordDict中任选（无限数量），能否凑齐s中0-j这个子串
         *
         *
         *      '' a p p l e p e n a p p l e
         *    ''
         * apple 1 0 0 0 0 1 0 0 0 0 0 0 0 1
         *   pen 1 0 0 0 0 1 0 0 1 0 0 0 0 1
         *
         *   最好用一维数组，因为后面的判断需要前面的所有判断（后一个apple需要判断之前这k个字符能否凑出来a p p l e p e n ，即需要保证一列为true）
         *
         *
         * @param s
         * @param wordDict
         * @return
         */
        public boolean wordBreak(String s, List<String> wordDict) {

            boolean[] dp = new boolean[s.length() + 1];


            dp[0] = true;

            for (int i = 1; i <= s.length() + 1; i++) {
                for (int j = 1; j <= wordDict.size(); j++) {
                    String word = wordDict.get(j - 1);
                    if (i > word.length()) {
                        String sub = s.substring(i - word.length() - 1, i - 1);
                        if (sub.equals(word) && dp[i - word.length() - 1]) {
                            dp[i - 1] = true;
                        } else {
                            dp[i - 1] = dp[i - 1];
                        }
                    }
                }
            }


            return dp[s.length()];
        }
    }
}
