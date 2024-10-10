package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/8 11:35
 * @注释
 */
public class LeetCode167 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(ints));
    }

    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int v = numbers[left] + numbers[right];
                if (v == target) {
                    return new int[]{left + 1, right + 1};
                } else if (v < target) {
                    left++;
                }else {
                    right--;
                }
            }
            return new int[]{-1, -1};
        }
    }
}
