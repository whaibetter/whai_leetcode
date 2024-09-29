package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 16:57
 * @注释
 */
public class LeetCode4 {

    @Test
    public void test()
    {
        Solution2 solution = new Solution2();
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        double medianSortedArrays = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    class Solution1 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length = nums1.length + nums2.length;
            int middle = length / 2;
            int index1 = 0;
            int index2 = 0;

            boolean flag = false;
            while (index1 + index2 < middle - 1) {
                if (nums1[index1] < nums2[index2]) {
                    flag = false;
                    index1++;
                } else {
                    flag = true;
                    index2++;
                }
            }

            if (length % 2 != 0) {
                return flag ? nums2[index2] : nums1[index1];
            }else {
                return (double) (nums1[index1] + nums2[index2]) / 2;
            }

        }
    }

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int[] nums = new int[nums1.length + nums2.length];
            int indexA = 0;
            int indexB = 0;
            int index = 0;
            while (indexA < nums1.length && indexB < nums2.length) {
                if (nums1[indexA] <= nums2[indexB]) {
                    nums[index++] = nums1[indexA++];
                }else {
                    nums[index++] = nums2[indexB++];
                }
            }

            while (indexA < nums1.length) {
                nums[index++] = nums1[indexA++];
            }
            while (indexB < nums2.length) {
                nums[index++] = nums2[indexB++];
            }

            int length = nums.length;
            return length % 2 == 1 ? (double) nums[length / 2] : (double) (nums[length/2 - 1] + nums[length/2]) / 2;

        }
    }

    class Solution2 {
        /**
         * 二分查找
         * @param nums1
         * @param nums2
         * @return
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {


            return 1;
        }
    }




}
