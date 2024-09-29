package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 16:11
 * @注释
 */
public class LeetCode88 {

    @Test
    public void test()
    {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        new Solution().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int index = 0;
            int[] res = new int[nums1.length];
            int index1 = 0;
            int index2 = 0;
            while (index1 < m && index2 < n) {
                if (nums1[index1] < nums2[index2]) {
                    res[index++] = nums1[index1++];
                }else {
                    res[index++] = nums2[index2++];
                }
            }

            while (index1 < m) {
                res[index++] = nums1[index1++];
            }
            while (index2 < n) {
                res[index++] = nums2[index2++];
            }

            System.arraycopy(res, 0, nums1, 0, index);
        }
    }
}
