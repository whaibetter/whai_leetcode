package cn.whaifree.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 12:55
 * @注释
 */
public class LCR090 {


    @Test
    public void test() {
        int[] nums = {1,7,9,2};
        System.out.println(new Solution().rob(nums));
    }

    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }

            //如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
            //环形街道
            return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
        }

        public int rob(int[] nums, int start, int end) {
            int dp[] = new int[nums.length];
            // dp【i】表示 到i最高能偷窃的个数
            // dp[start]=1
            // dp[start+1]=1
            // dp[i] = max(dp[i-2]+nums[i],dp[i-1])
            dp[start] = nums[start];
            dp[start + 1] = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {

                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[end];

        }
    }

}
