package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/2 11:40
 * @注释
 */
public class LeetCode1005 {

    @Test
    public void test() {
        int[] nums = new int[]{5,6,9,-3,3};
        int k =2;
        int res = new Solution1().largestSumAfterKNegations(nums, k);
        System.out.println(res);
    }

    class Solution {
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 从小到大排序，把前面的负数替换后，如果k是否有剩余
            Arrays.sort(nums);
            int sum = 0;
            int minInNums = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                minInNums = Math.min(minInNums, nums[i]);
                sum += nums[i];
            }

            if ((k % 2 == 1 )) {
                sum = sum - minInNums - minInNums;
            }
            return sum;

        }
    }

    class Solution1 {
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 按照绝对值排序
            nums = Arrays.stream(nums).boxed().sorted(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Math.abs(o2) - Math.abs(o1);
                }
            }).mapToInt(Integer::intValue).toArray();


            int sum = 0;
            // 9 -8 -7 6 4 3
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                sum += nums[i];
            }

            if (k % 2 == 1 ) {
                sum -= nums[nums.length - 1] + nums[nums.length - 1];
            }
            return sum;

        }
    }
}
