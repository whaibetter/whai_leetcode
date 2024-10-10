package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Random;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/10 11:17
 * @注释
 */
public class LeetCode215 {

    @Test
    public void test() {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution solution = new Solution();
        int kthLargest = solution.findKthLargest(nums, k);
        System.out.println(kthLargest);
    }

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            return find(nums, 0, nums.length - 1, nums.length - k);
        }

        public int find(int[] nums, int start, int end, int k) {
            if (start > end) {
                return nums[end];
            }

            int q = new Random().nextInt(end - start + 1) + start;

            swap(nums, q, end);

            int base = nums[end];
            int left = start;
            int right = end;
            while (left < right) {

                // 从左往右遍历，当左指针指向的元素小于等于基数时，i++。左指针持续向右移动
                while (nums[left] >= base && left < right) {
                    left++;
                }
                // 从右往左遍历，当右指针指向的元素大于等于基数时，j--。右指针持续向左移动
                while (nums[right] <= base && left < right) {
                    right--;
                }
                if (left < right) {
                    // 当左右两个指针停下来时，交换两个元素
                    swap(nums, left, right);
                }
            }
            swap(nums, left, end);

            // 从大到小排序，如果左边k-1个，则left就是第k个，左边k-1个比他大
            if (left == k - 1) {
                return nums[left];
            }
            // 左边的数量太少了，往右边找
            if (left < k - 1) {
                return find(nums, left + 1, end, k);
            }
            return find(nums, start, left - 1, k);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


}
