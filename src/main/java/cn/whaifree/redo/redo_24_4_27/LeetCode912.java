package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/29 12:30
 * @注释
 */
public class LeetCode912 {
    @Test
    public void test() {
//        new ArrayList<>().iterator().next()
        int[] nums = new int[]{5,1,1,2,0,0};
        int[] res = new Solution1().sortArray(nums);
        System.out.println(Arrays.toString(res));
    }

    class Solution {
        public int[] sortArray(int[] nums) {
            if (nums.length <= 1) {
                return nums;
            }
            int mid = nums.length / 2;
            int[] left = Arrays.copyOfRange(nums, 0, mid);
            int[] right = Arrays.copyOfRange(nums, mid, nums.length);
            left = sortArray(left);
            right = sortArray(right);
            return merge(left, right);
        }

        public int[] merge(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length + nums2.length];
            int index1 = 0;
            int index2 = 0;
            while (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] < nums2[index2]) {
                    res[index1 + index2] = nums1[index1];
                    index1++;
                } else {
                    res[index1 + index2] = nums2[index2];
                    index2++;
                }
            }
            while (index1 < nums1.length) {
                res[index1 + index2] = nums1[index1];
                index1++;
            }
            while (index2 < nums2.length) {
                res[index1 + index2] = nums2[index2];
                index2++;
            }
            return res;
        }
    }

    class Solution1 {
        public int[] sortArray(int[] nums) {
            sort(nums, 0, nums.length - 1);
            return nums;
        }

        public void sort(int[] nums, int left, int right) {

        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


}
