package cn.whaifree.redo.redo_24_3_9;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/9 13:38
 * @注释
 */
public class LeetCode763 {

    @Test
    public void test() {
        new Solution().partitionLabels("ababcbacadefegdehijhklij").forEach(
                i -> System.out.println(i)
        );
    }

    class Solution {
        public List<Integer> partitionLabels(String s) {

            List<Integer> res = new ArrayList<>();
            int[] map = new int[26];

            // 计算最晚出现的索引
            // 如果当前i==最晚出现的索引，需要划分一次
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                map[chars[i] - 'a'] = i;
            }

            int left = -1;
            int far = 0;
            for (int i = 0; i < chars.length; i++) {
                // 遍历的过程中，找到前面区间的最远边界
                far = Math.max(far, map[chars[i] - 'a']);
                if (i == far) {
                    res.add(i - left);
                    left = i;
                }
            }
            return res;
        }
    }
}
