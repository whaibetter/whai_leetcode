package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 12:15
 * @注释
 */
public class LeetCode189 {
    @Test
    public void test()
    {
        int[] nums = new int[]{-1};
        new Solution1().rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }



    class Solution {
        public void rotate(int[] nums, int k) {
            int[] newNums = new int[nums.length];
            for (int i = 0; i < newNums.length; i++) {
                newNums[(i + k) % nums.length] = nums[i];
            }
            System.arraycopy(newNums, 0, nums, 0, nums.length);

        }

    }

    class Solution1 {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
