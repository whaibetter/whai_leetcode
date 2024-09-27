package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 18:12
 * @注释
 */
public class LeetCode1005 {

    public static void main(String[] args) {
        LeetCode1005 leetCode1005 = new LeetCode1005();
        int[] nums = {2,-3,-1,5,-4};
        int k = 2;
        int i = leetCode1005.new Solution().largestSumAfterKNegations(nums, k);
        System.out.println(i);
    }

    class Solution {
        public int largestSumAfterKNegations(int[] nums, int k) {
            Arrays.sort(nums);

            // 偶数
            // 奇数
            // 最小的绝对值
            int absMin = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                absMin = Math.min(absMin, Math.abs(nums[i]));
            }

            int sum = Arrays.stream(nums).sum();
            // 如果还有k没使用到
            // 按照绝对值排序
            if (k % 2 == 0) {
                return sum;
            }else {
                return sum - absMin * 2;
            }
        }
    }
}
