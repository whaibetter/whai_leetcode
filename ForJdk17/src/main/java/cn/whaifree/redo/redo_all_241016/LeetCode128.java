package cn.whaifree.redo.redo_all_241016;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 15:00
 * @注释
 */
public class LeetCode128 {

    class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            int res = 0;
            for (int num : set) {
                if (!set.contains(num - 1)) {
                    int currentNum = num;
                    int currentLength = 1;
                    while (set.contains(currentNum + 1)) {
                        currentNum++;
                        currentLength++;
                    }
                    res = Math.max(res, currentLength);
                }
            }

            return res;
        }
    }
}
