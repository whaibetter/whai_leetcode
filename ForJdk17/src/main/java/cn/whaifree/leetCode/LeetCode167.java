package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/19 23:02
 * @注释
 */
public class LeetCode167 {

    @Test
    public void test() {

        Solution1 solution = new Solution1();
        int[] ints = solution.twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(ints[0] + " " + ints[1]);
    }

    class Solution {
        public int[] twoSum(int[] numbers, int target) {

            for (int i = 0; i < numbers.length; i++) {
                int tar = target - numbers[i];
                int j = binarySearch(numbers, tar);
                if (j != -1 && i != j) {
                    return i < j ? new int[]{i + 1, j + 1} : new int[]{j + 1, i + 1};
                }
            }
            return null;
        }

        public int binarySearch(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }

    class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    return new int[]{left + 1, right + 1};
                } else if (sum < target) {
                    left++;
                }else {
                    right--;
                }
            }
            return null;
        }
    }
}
