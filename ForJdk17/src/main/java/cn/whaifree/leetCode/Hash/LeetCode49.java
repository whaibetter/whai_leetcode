package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 13:22
 * @注释
 */
public class LeetCode49 {

    @Test
    public void test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution().groupAnagrams(strs));
    }


    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] charArray = str.toCharArray(); // 排序后 key 一样
                Arrays.sort(charArray);
                String key = new String(charArray);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }
    class Solution1 {
        /**
         * 统计每个字母出现的个数，并按照a2b1c4这种格式变成String，作为key
         *
         * 统计次数ON*M
         * @param strs
         * @return
         */
        public List<List<String>> groupAnagrams(String[] strs) {

            List<List<String>> res = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                int[] count = new int[26];
                for (int i = 0; i < str.length(); i++) {
                    int index = str.charAt(i) - 'a';
                    count[index]++;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < count.length; i++) {
                    if (count[i] != 0) {
                        // a12b19这种形式
                        stringBuilder.append((char) ('a' + i));
                        stringBuilder.append(count[i]);
                    }
                }

                String key = stringBuilder.toString();
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }



//
//    class Solution {
//
//        static List<Integer> anagrams = null;
//        static List<List<String>> res = null;
//        public List<List<String>> groupAnagrams(String[] strs) {
//            int[][] nums = new int[strs.length][26];
//            for (int i = 0; i < strs.length; i++) {
//                String str = strs[i];
//                for (int j = 0; j < str.length(); j++) {
//                    nums[i][str.charAt(j) - 'a']++;
//                }
//            }
//
//            anagrams = new ArrayList<>();
//            res = new ArrayList<>();
//            backTracking(strs, nums, 0);
//            return res;
//        }
//
//        public static void backTracking(String[] strs, int[][] map, int start) {
//            if (start >= map.length - 1) {
//                List<String> anagram = new ArrayList<>();
//                for (Integer a : anagrams) {
//                    anagram.add(strs[a]);
//                }
//                res.add(new ArrayList<>(anagram));
//                return;
//            }
//
//            if (!anagrams.isEmpty() && !match(map, anagrams.get(anagrams.size() - 1), start)) {
//                return;
//            }
//
//            for (int i = start; i < map.length; i++) {
//                anagrams.add(i);
//                backTracking(strs, map, i + 1);
//                anagrams.remove(anagrams.size() - 1);
//            }
//
//        }
//
//        public static boolean match(int[][] map, int a, int b) {
//            int[] A = map[a];
//            int[] B = map[b];
//            for (int i = 0; i < 26; i++) {
//                if (A[i] != B[i]) return false;
//            }
//            return true;
//        }
//    }
}
