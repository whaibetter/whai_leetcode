package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/18 12:14
 * @注释
 */
public class LeetCode167 {

    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ints = new Solution().twoSum(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int search = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
                if (search != -1) {
                    return new int[]{i + 1, search + 1};
                }
            }
            return new int[]{};
        }

        public int binarySearch(int[] nums, int left, int right, int target) {
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }else if (nums[mid] < target) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}
