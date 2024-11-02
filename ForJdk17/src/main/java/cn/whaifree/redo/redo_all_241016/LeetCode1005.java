package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 14:08
 * @注释
 */
public class LeetCode1005 {

    @Test
    public void test() {
        int[] nums = {4,-5,4,-5,9,4,5};
        int k = 1;
        int result = new Solution().largestSumAfterKNegations(nums, k);
        System.out.println(result);
    }

    class Solution {
        public int largestSumAfterKNegations(int[] nums, int k) {
            Arrays.sort(nums);

            int res = 0;
            int minAbs = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                minAbs = Math.min(minAbs, nums[i]);
                res += nums[i];
            }
            if (k % 2 == 0) {
                // 偶数
                return res;
            }else {
                return res - minAbs * 2;
            }
        }
    }
}
