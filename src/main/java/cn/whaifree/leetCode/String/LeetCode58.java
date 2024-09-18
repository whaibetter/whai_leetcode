package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 22:04
 * @注释
 */
public class LeetCode58 {
    @Test
    public void test() {
        Solution1 solution = new Solution1();
        int i = solution.lengthOfLastWord("   fly me   to   the moon  ");
        System.out.println(i);
    }

    class Solution {
        public int lengthOfLastWord(String s) {
            String trim = s.trim();
            String[] split = trim.split(" ");
            return split[split.length - 1].length();
        }
    }

    class Solution1 {
        public int lengthOfLastWord(String s) {
            s = s.trim();
            int len = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != ' ') {
                    len++;
                }else {
                    break;
                }
            }
            return len;

        }
    }
}
