package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 15:44
 * @注释
 */
public class LeetCode31 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 2}; // 5 1 1 2 3
        new Solution().nextPermutation(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    class Solution {
        /**
         * 1 2 8 2 1
         * 1 8 1 2 2
         *
         *  1 2 3 8 2 1
         *  1 2 3 1 2 8
         *
         * 从右往左 找到第一个递增i i-1
         *
         * 交换i i-1，
         *
         * 拿出i，把他放到后面那一串递减的合适位置
         * 1 2 8 2 1
         * 1 8 2 2 1 // 找到第一个比他小的，替换
         * 1 8 1 2 2
         *
         *
         * 2 1 3
         *
         * @param nums
         */
        public void nextPermutation(int[] nums) {
            int index = Integer.MAX_VALUE;
            for (int i = nums.length - 1; i >= 1; i--) {
                if (nums[i] > nums[i - 1]) {
                    index = i;
                    break;
                }
            }
            if (index == Integer.MAX_VALUE) { // 3 2 1 直接逆转
                reverse(nums, 0, nums.length - 1);
                return;
            }



            int j;
            for (j = nums.length - 1; j > index - 1; j--) {
                if (nums[j] > nums[index - 1]) {
                    break;
                }
            }
            swap(nums, index - 1, j);
            reverse(nums, index, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start++, end--);
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    static List<Object> list = null;
    public static void main(String[] args) throws InterruptedException {
        list = new ArrayList<>();
        while (true) {
            Thread.sleep(100);

            int[] ints = new int[100000];
            list.add(ints);
        }
    }

}
