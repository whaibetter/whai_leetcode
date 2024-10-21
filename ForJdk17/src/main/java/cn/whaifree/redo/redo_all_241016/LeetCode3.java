package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/21 12:46
 * @注释
 */
public class LeetCode3 {

    @Test
    public void test() {
        String s = "pwwkew";
        int result = new Solution().lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            char[] charArray = s.toCharArray();
            int left = 0;
            int right = 0;
            Set<Character> set = new HashSet<>();
            int max = 0;
            while (right < s.length()) {
                char c = charArray[right];
                while (left < right && set.contains(c)) {
                    set.remove(charArray[left]);
                    left++;
                }
                max = Math.max(max, right - left + 1);
                set.add(c);
                right++;
            }
            return max;
        }
    }
}
