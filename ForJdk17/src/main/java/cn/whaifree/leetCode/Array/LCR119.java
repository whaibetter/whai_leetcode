package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 16:37
 * @注释
 */
public class LCR119 {
    @Test
    public void test() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(new Solution().longestConsecutive(nums));
    }

    class Solution {
        public int longestConsecutive(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            int max = 0;
            for (int num : nums) {
                if (set.contains(num- 1)) { // 1 2 3 4 只要保证set里没有0
                    continue;
                }
//                int base = num;
//                int tmp = 0;
//                while (set.contains(base++)) {
//                    tmp++;
//                }
//                max = Math.max(max, tmp);

                int maxnum = num;
                while (set.contains(maxnum + 1)) {
                    maxnum++;
                }
                max = Math.max(max, maxnum - num + 1);
            }
            return max;

        }
    }
}
