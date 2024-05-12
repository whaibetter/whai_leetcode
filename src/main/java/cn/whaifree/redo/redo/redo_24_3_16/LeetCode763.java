package cn.whaifree.redo.redo.redo_24_3_16;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 17:53
 * @注释
 */
public class LeetCode763 {

    @Test
    public void test() {
        String s = "ababcbacadefegdehijhklij";
        Solution solution = new Solution();
        List<Integer> list = solution.partitionLabels(s);
        System.out.println(list);
    }

    class Solution {
        public List<Integer> partitionLabels(String s) {

            // 统计每个字符最后出现的位置
            int[] map = new int[26];
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                map[chars[i] - 'a'] = i;
            }

            List<Integer> list = new ArrayList<>();

            int left = 0;
            int rightMax = 0;

            for (int i = 0; i < chars.length; i++) {
                rightMax = Math.max(map[chars[i] - 'a'], rightMax);
                if (i == map[chars[i] - 'a'] && rightMax == i) {
                    list.add(rightMax - left + 1);
                    left = rightMax + 1;
                }
            }

            return list;

        }
    }
}
