package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 13:55
 * @注释
 */
public class LeetCode49 {

    @Test
    public void test() {
        String[] strs = {"abbbbbbbbbbb","aaaaaaaaaaab"};
        List<List<String>> result = new Solution().groupAnagrams(strs);
        for (List<String> list : result) {
            System.out.println(list);
        }
    }

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {

            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {

                int[] counts = new int[26];
                String item = strs[i];
                for (int j = 0; j < item.length(); j++) {
                    counts[item.charAt(j) - 'a']++;
                }

                StringBuilder str = new StringBuilder();
                for (int j = 0; j < counts.length; j++) {
                    if (counts[j] != 0) {
                        str.append((char) ('a' + j)).append(counts[j]);
                    }
                }
                if (!map.containsKey(str.toString())) {
                    map.put(str.toString(), new ArrayList<>());
                }
                map.get(str.toString()).add(strs[i]);
            }

            List<List<String>> result = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                result.add(entry.getValue());
            }
            return result;
        }
    }
}
