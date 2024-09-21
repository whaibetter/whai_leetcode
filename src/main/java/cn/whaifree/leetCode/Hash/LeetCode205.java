package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 13:32
 * @注释
 */
public class LeetCode205 {
    @Test
    public void test() {
        String s = "badc";
        String t = "baba";
        System.out.println(new Solution().isIsomorphic(s, t));
    }

    class Solution {
        public boolean isIsomorphic(String s, String t) {
            return is(s, t) && is(t, s);
        }

        public boolean is(String s, String t) {
            HashMap<Character, Character> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char key = s.charAt(i);
                if (!map.containsKey(key)) {
                    map.put(key, t.charAt(i));
                } else if (map.get(key) != t.charAt(i)) {
                    return false;
                }
            }
            return true;

        }


    }
    class Solution1 {
        /**
         * 每个字符第一次出现的位置都一样，就能保证同构成
         * abc c和d第一次出现的 位置为1和2，不一样会false
         * cdd
         *
         * indexOf时间复杂度oN
         * @param s
         * @param t
         * @return
         */
        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            for (int i = 0; i < s.length(); i++) {
                char s1 = s.charAt(i);
                char t1 = t.charAt(i);
                if (s.indexOf(s1) != t.indexOf(t1)) {
                    return false;
                }
            }
            return true;
        }
    }
}
