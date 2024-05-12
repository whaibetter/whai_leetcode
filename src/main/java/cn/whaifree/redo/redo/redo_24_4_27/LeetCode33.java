package cn.whaifree.redo.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/27 11:37
 * @注释
 */
public class LeetCode33 {

    @Test
    public void test() {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        int i = new Solution().search(nums, target);
        System.out.println(i);
    }

    class Solution {
        public int search(int[] nums, int target) {
            // 二分查找
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                // nums = nums1 + nums2
                // 4,5,6,7,0,1,2
                if (nums[mid] > nums[right]) { // nums1和nums2的交接处在mid右边
                    // nums1
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // nums2
                    if (nums[mid] < target && target <= nums[right]) { // nums1和nums2的交接处在mid左边
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }


            return -1;
        }

    }
}
