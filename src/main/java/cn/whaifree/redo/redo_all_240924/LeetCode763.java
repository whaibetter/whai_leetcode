package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 12:49
 * @注释
 */
public class LeetCode763 {

    @Test
    public void test() {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> list = new Solution().partitionLabels(s);
        System.out.println(list);
    }

    class Solution {
        public List<Integer> partitionLabels(String s) {
            char[] charArray = s.toCharArray();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < charArray.length; i++) {
                map.put(charArray[i], i);
            }
            List<Integer> list = new ArrayList<>();
            int left = 0;
            int right = 0;
            int rightMax = 0;
            while (right < charArray.length) {
                Integer last = map.get(charArray[right]);
                rightMax = Math.max(rightMax, last); // 这个区间内最远的出现范围
                if (right == last && right == rightMax) {
                    list.add(right - left + 1);
                    left = right + 1;
                }
                right++;
            }
            return list;
        }
    }
}
