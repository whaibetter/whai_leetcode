package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/23 11:56
 * @注释
 */
public class LeetCode674 {
    @Test
    public void test()
    {

        int[] nums = new int[]{1,1,1,1};
        int lengthOfLCIS = new Solution1().findLengthOfLCIS(nums);
        System.out.println(lengthOfLCIS);
    }

    class Solution2 {
        /**
         * 如果当前元素大于前一个元素，则递增计数，并将计数与结果中的最大值进行比较更新。
         * 如果当前元素不大于前一个元素，则将计数重置为1。
         * @param nums
         * @return
         */
        public int findLengthOfLCIS(int[] nums) {
            int res = 1;
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    count++;
                    res = Math.max(res, count);
                } else {
                    count = 1;
                }
            }
            return res;
        }
    }

    class Solution {


        /**
         * 最长且 连续递增的子序列
         * @param nums
         * @return
         */
        public int findLengthOfLCIS(int[] nums) {
            // 找到所有递增区间
            int left = 0;
            int right = 0;
            int maxLength = 0;
            while (right < nums.length - 1) {
                if (nums[right + 1] <= nums[right]) {
                    maxLength = Math.max(maxLength, right - left + 1);
                    right++;
                    left = right;
                }else {
                    right++;
                }
            }

            return Math.max(maxLength, right - left + 1);
        }
    }

    class Solution1 {
        public int findLengthOfLCIS(int[] nums) {
            /**
             * dp[i] 表示可包含i的最长子序列长度
             * if nums[i] > nums[i-1] 递增
             *      dp[i] = dp[i-1]+1
             * else
             *      dp[i] = 1 一旦出现递减，因为前面的连续递增是不能用的，需要直接重置
             */
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int ans = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i-1]) {
                    dp[i] = dp[i-1] + 1;
                }else {
                    dp[i] = 1;
                }
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }


    }
}
