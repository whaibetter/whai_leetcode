package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/8 11:53
 * @注释
 */
public class LeetCode189 {

    @Test
    public void test() {
        int[] nums = new int[]{-1};
        int k = 2;
        new Solution().rotate(nums, k);
        for (int i : nums) {
            System.out.println(i);
        }
    }

    // 7 6 5 4 3 2 1
    // 5 6 7 1 2 3 4
    class Solution {
        public void rotate(int[] nums, int k) {
            if (k > nums.length) {
                k = k % nums.length;
            }
            int start = nums.length - k;
            int[] newN = new int[nums.length * 2];
            System.arraycopy(nums, 0, newN, 0, nums.length);
            System.arraycopy(nums, 0, newN, nums.length, nums.length);
            System.arraycopy(newN, start, nums, 0, nums.length);
        }
    }
}
