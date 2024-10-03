package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/30 10:37
 * @注释
 */
public class LeetCode33 {

    @Test
    public void test() {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(new Solution().search(nums, target));
    }

    class Solution {
        /**
         *
         * |     |  |      |
         *  3 | 4    1 | 2
         *   A   B    C  D
         *
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {

            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] <= nums[mid]) { // A或者B
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid - 1; // A
                    } else {
                        left = mid + 1; // B
                    }
                } else { // C或者D
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return nums[left] == target ? left : -1;
        }
    }
}
