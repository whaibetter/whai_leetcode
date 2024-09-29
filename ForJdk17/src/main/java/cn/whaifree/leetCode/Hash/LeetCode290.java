package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 13:09
 * @注释
 */
public class LeetCode290 {
    @Test
    public void test() {
        String pattern = "abbc";
        String s = "dog cat cat dog";
        System.out.println(new Solution().wordPattern(pattern, s));
    }

    class Solution {
        public boolean wordPattern(String pattern, String s) {
            Map<Character, String> map = new HashMap<>();
            Map<String, Character> map2 = new HashMap<>();
            s = s.trim();
            String[] split = s.split(" ");
            if (pattern.length() != split.length) {
                return false;
            }

            int len = pattern.length();
            for (int i = 0; i < len; i++) {
                char key1 = pattern.charAt(i);
                String key2 = split[i];
                if (!map.containsKey(key1)) {
                    map.put(key1, key2);
                }
                if (!map2.containsKey(key2)) {
                    map2.put(key2, key1);
                }
                if (!map.get(key1).equals(key2) || map2.get(key2) != key1) {
                    return false;
                }
            }
            return true;
        }
    }
}
