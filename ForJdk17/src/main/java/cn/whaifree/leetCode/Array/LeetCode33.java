package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/15 10:32
 * @注释
 */
public class LeetCode33 {


    @Test
    public void test()
    {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int i = new Solution().search(nums, target);
        System.out.println(i);
    }

    class Solution {
        /**
         *
         * @param nums 旋转后的值
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {

            // 二分查找，关键在于如何找到两个递增区间的交结处k
            // 判断left和mid的关系
            // 如果nums[left] < nums[mid] 即mid在left-k的区间
            //      if(target<nums[mid] && target>nums[left]) 在left到mid区间
            //      else  mid到right区间
            // 如果nums[left] > nums[mid] 即mid在k-right的区间
            //      if(target>nums[mid]&&target<nums[right]) mid到right之间
            //      else  left到mid之间
            int left = 0, right = nums.length - 1;


            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] <= nums[mid] ) {
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
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
//
//    class Solution1 {
//        public int search(int[] nums, int target) {
//
//        }
//    }

}
