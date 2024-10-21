package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/21 14:13
 * @注释
 */
public class LeetCode567 {

    /**
     * 超时
     */
    @Test
    public void test() {
        String s1 = "adc";
        String s2 = "dcda";
        boolean result = new Solution().checkInclusion(s1, s2);
        System.out.println(result);
    }

    class Solution {


        /**
         * @param s1
         * @param s2
         * @return
         */
        public boolean checkInclusion(String s1, String s2) {
            // 获取s1的全部排列，再到s2中找有没有对于的子串
            List<String> stringSub = getStringSub(s1);
            for (String s : stringSub) {
                int len = s1.length();
                for (int i = 0; i <= s2.length() - len; i++) {
                    if (s2.substring(i, i + len).equals(s)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public List<String> getStringSub(String s1) {
            ArrayList<String> res = new ArrayList<>();
            backTracking(res, s1);
            return res;
        }

        StringBuilder path = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        public void backTracking(List<String> res,  String s) {
            if (path.length() >= s.length()) {
                res.add(path.toString());
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (set.contains(i)) {
                    continue;
                }
                set.add(i);
                path.append(s.charAt(i));
                backTracking(res, s);
                path.deleteCharAt(path.length() - 1);
                set.remove(i);
            }
        }
    }

    @Test
    public void test1() {
        String s1 = "ab";
        String s2 = "eidboaooo";
        boolean result = new Solution1().checkInclusion(s1, s2);
        System.out.println(result);
    }

    class Solution1 {
        /**
         * 需要同时考虑 s1的map和s2的map匹配，同时还有已经完全匹配的个数；
         * @param s1
         * @param s2
         * @return
         */
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Integer> need = new HashMap<>();
            for (int i = 0; i < s1.length(); i++) {
                need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
            }
            int left = 0;
            int right = 0;
            Map<Character, Integer> window = new HashMap<>();

            int validCount = 0; // 记录有效个数(某个字符对应的数量和need一致)，如果==need.size直接返回
            char[] s2CharArray = s2.toCharArray();
            while (right < s2.length()) {
                // 右边指针不断探，加入window统计出现个数
                char c = s2CharArray[right];
                right++;
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        validCount++;
                        if (validCount == need.size()) {
                            return true;
                        }
                    }
                }
                // 左边指针收缩 关键在于找到进入while left 的循环条件
                while (right - left >= s1.length()) {
                    char cha = s2CharArray[left];
                    left++;
                    if (need.containsKey(cha)) {
                        if (window.get(cha).equals(need.get(cha))) {
                            validCount--;
                        }
                        window.put(cha, window.get(cha) - 1);
                    }
                }

            }
            return false;
        }
    }
}
