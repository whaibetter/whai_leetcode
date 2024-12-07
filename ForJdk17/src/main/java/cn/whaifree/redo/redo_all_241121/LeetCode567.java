package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/7 10:54
 * @注释
 */
public class LeetCode567 {
    @Test
    public void test() {
        String s1 = "ab";
        String s2 = "eidboaoo";
        boolean result = new Solution().checkInclusion(s1, s2);
        System.out.println(result);
    }

    class Solution {
        /**
         * 滑动窗口，双指针，匹配出s1包含的数量
         *
         * @param s1
         * @param s2
         * @return
         */
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Integer> s1NeedCount = new HashMap<>();
            // 统计s1中每个字符的数量
            for (int i = 0; i < s1.length(); i++) {
                s1NeedCount.put(s1.charAt(i), s1NeedCount.getOrDefault(s1.charAt(i), 0) + 1);
            }

            Map<Character, Integer> windowsCount = new HashMap<>();
            int left = 0;
            int right = 0;
            int validCount = 0;
            while (right < s2.length()) {
                char key = s2.charAt(right);

                // 如果是需要的,放到windowsCount中
                if (s1NeedCount.containsKey(key)) {
                    windowsCount.put(key, windowsCount.getOrDefault(key, 0) + 1);
                    // 如果窗口刚刚好符合, s1中的字符数量和windows中的字符数量一致
                    if (windowsCount.get(key).equals(s1NeedCount.get(key))) {
                        validCount++;
                        if (validCount == s1NeedCount.size()) {
                            return true;
                        }
                    }

                }

                while (right - left >= s1.length() - 1) {
                    char leftKey = s2.charAt(left);
                    if (s1NeedCount.containsKey(leftKey)) {
                        if (windowsCount.get(leftKey).equals(s1NeedCount.get(leftKey))) {
                            // 走着走着，有些字符的不完全匹配了
                            validCount--;
                        }
                        windowsCount.put(leftKey, windowsCount.get(leftKey) - 1); // 窗口-1
                    }
                    left++;
                }
                right++;
            }
            return false;
        }
    }
}
