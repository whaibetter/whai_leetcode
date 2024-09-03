package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/3 22:41
 * @注释
 */
public class LeetCode300 {

    @Test
    public void test() {
        int[] nums = {10,9,2,5,3,7,101,18};
        Solution1 solution = new Solution1();
        int i = solution.lengthOfLIS(nums);
        System.out.println(i);
    }

    class Solution {


        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            /**
             * **dp[i]表示i之前包括i的以nums[i]结尾(每次都需要使用nums[j]来同nums[i]比较)的最长递增子序列的长度**
             * 子序列 确定某个数，让前面不断与他比较
             */
            for (int i = 1; i < nums.length; i++) {
                for (int j = i; j >= 0; j--) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            return Arrays.stream(dp).max().getAsInt();
        }


    }

    class Solution1 {

        /**
         * dp[i] 表示0-i已经出现的最长
         *
         * [10,9,2,5,3,7,101,18]
         *   1 1 1 2 2 3  4   4
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {


            int[] dp = new int[nums.length];

            Arrays.fill(dp, 1);
            for (int i = 1; i < nums.length; i++) {

                // 不断往后搜索，如果比当前值小，就有可能是更长的值
                int index = i - 1;
                while (index >= 0) {
                    if (nums[i] >  nums[index]) {
                        dp[i] = Math.max(dp[index] + 1, dp[i]);
                    }
                    index--;
                }

            }
            return Arrays.stream(dp).max().getAsInt();
        }
    }
}
