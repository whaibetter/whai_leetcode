package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.HashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 18:32
 * @注释
 */
public class LeetCode3 {

    @Test
    public void test() {
        Solution solution = new Solution();
        String s = "abcabcbb";
        int result = solution.lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashMap<Character, Boolean> map = new HashMap<>();
            int left = 0;
            int right = 0;

            int maxLen = 0;
            while (right < s.length()) {
                char item = s.charAt(right);
                if (map.containsKey(item) && map.get(item)) {
                    // 已经存在
                    maxLen = Math.max(maxLen, right - left);
                    while (map.get(item)) {
                        map.put(s.charAt(left), false);
                        left++;
                    }
                }
                map.put(item, true);
                right++;
            }

            int mapSize = 0;
            for (Character c : map.keySet()) {
                if (map.get(c)) {
                    mapSize++;
                }
            }
            // 有可能在后面才能取到最大值

            return Math.max(mapSize, maxLen);


        }
    }
}
