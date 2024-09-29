package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/17 21:33
 * @注释
 */
public class LeetCode416 {

    @Test
    public void test()
    {
        int[] nums = {1,5,11,5};
        System.out.println(canPartition(nums));
    }

    /**
     *
     * 背包容量 sum/2 11
     * 物品 nums
     *
     * dp[i][j]表示从0-i中任意取放入容量为j的背包的最大价值
     *
     *
     * dp
     *   0 1 2 3 4 5 6 7 8 9 10 11
     * 0 0 1 1 1 1 1 1 1 1 1 1  1
     * 1 0 1 1 1 1 5 6 6 6 6 6  6
     * 2 0 1 1 1 1 5 6 6 6 6 6  11
     * 3 0 1 1 1 1 5 6 6 6 6 10 11
     *
     *
     * if 能放进去
     *  不放物品i的最大价值 dp[i-1][j]
     *  放物品i的最大价值  dp[i-1][j-weight[i]] + value[i]
     * else
     *  dp[i-1][j]
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums)
    {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int half = sum / 2;

        int[][] dp = new int[nums.length][half + 1];
        for (int i = nums[0]; i <= half; i++) {
            dp[0][i] = nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= half; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    /**
                     * 不放                  dp[i-1][j]
                     * 放i,在i-1中获取大价值基础上加   dp[i - 1][j - nums[i]] + nums[i]
                     */
                }

                if (dp[i][j] == half) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean canPartition1(int[] nums)
    {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int half = sum / 2;

        int[] dp = new int[half + 1];
        for (int i = nums[0]; i <= half; i++) {
            dp[i] = nums[0];
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                /**
                 * https://www.programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html#%E6%80%9D%E8%B7%AF
                 * 如果正序遍历
                 * dp[1] = dp[1 - weight[0]] + value[0] = 15
                 * dp[2] = dp[2 - weight[0]] + value[0] = 30
                 * 此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
                 */
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (dp[j] == half) {
                    return true;
                }
            }
        }
        return false;

    }
}
