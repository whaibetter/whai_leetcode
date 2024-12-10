package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/21 11:30
 * @注释
 */
public class LeetCode3 {

    @Test
    public void test() {
        String s = "abcabcbb";
        System.out.println(new Solution1().lengthOfLongestSubstring(s));

    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }

            // s 由英文字母、数字、符号和空格组成
            Set<Character> set = new TreeSet<>(); // 保持原来的顺序
            int right = 0;
            int left = 0;
            int maxLength = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                if (set.contains(c)) {
                    maxLength = Math.max(maxLength, right - left);
                    while (set.contains(c)) {
                        set.remove(s.charAt(left));
                        left++;
                    }
                }
                set.add(c);
                right++;
            }
            return Math.max(set.size(), maxLength);
        }
    }

    class Solution1 {
        /**
         * <img src="http://so9vd9h8j.hd-bkt.clouddn.com/image-20240421115141359.png">
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }

            boolean[] map = new boolean[95];

            // s 由英文字母、数字、符号和空格组成
            // Set<Character> set = new TreeSet<>(); // 保持原来的顺序
            int right = 0;
            int left = 0;
            int maxLength = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                if (map[c - 32]) {
                    maxLength = Math.max(maxLength, right - left);
                    while (map[c - 32]) {
                        map[s.charAt(left) - 32] = false;
                        left++;
                    }
                }
                map[c - 32] = true;
                right++;
            }

            int mapSize= 0;
            for (boolean b : map) {
                if (b) {
                    mapSize++;
                }
            }

            return Math.max(mapSize, maxLength);
        }
    }
}
