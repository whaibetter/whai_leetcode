package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 14:43
 * @注释
 */
public class LeetCode704 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {1};
        int target = 8;
        int result = solution.search(nums, target);
        System.out.println(result);
    }


    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}
