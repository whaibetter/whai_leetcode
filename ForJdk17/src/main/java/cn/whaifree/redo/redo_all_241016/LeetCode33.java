package cn.whaifree.redo.redo_all_241016;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 14:11
 * @注释
 */
public class LeetCode33 {

    class Solution {
        /**
         *
         *
         *
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] <= nums[mid]) {
                    //  |4 5 6 7 8 | 0 1
                    // 目标值在4-6，那么就右边指针mid-1 ，否则不在这个指针，去另一个乱序区间找
                    // 【   】mid【  k 】
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // 4 5 |0 1 2 3|
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return -1;
        }
    }


    class Solution1 {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] >= nums[left]) {
                    // |        mid     k            |
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    }else {
                        left = mid + 1;
                    }
                }else {
                    // |           k   mid        |
                    if (nums[right] >= target && target > nums[mid]) {
                        left = mid + 1;
                    }else {
                        right = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
}
