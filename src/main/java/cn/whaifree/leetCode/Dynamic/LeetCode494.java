package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/18 16:17
 * @注释
 */
public class LeetCode494 {

    @Test
    public void test()
    {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(new Solution2().findTargetSumWays(nums, target));
    }

    class Solution {
        /**
         * 背包容量为3
         * left（+1的数量）-right（-1的数量） = target
         * left-right = target
         * left+right = sum （right = sum -left）
         * left = (target + sum)/2
         * 表示正数有多少个是固定的，就是我们的背包容量
         *
         *
         * 使用nums[i] 里面全是1 装满容量为left的背包，有几种方法
         *
         * if j> nums[i]
         *      dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
         *                  不放          放
         * else
         *      dp[i][j] = dp[i-1][j]
         * 1,1,1,1,1
         *
         *   0 1 2 3 4
         * 0 1 1 0 0 0
         * 1 0 0 1 0 0
         * 2 0 3
         * 3
         * 4
         *
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays(int[] nums, int target) {

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 总和还比不过绝对值，1不够用
            if(sum < Math.abs(target)){
                return 0;
            }

            // left 为+1的数量 +1的数量必须是整数
            if((sum + target) % 2 != 0) {
                return 0;
            }

            int left = (sum + target) >> 1; // (sum + target) / 2;


            int length = nums.length;
            int[][] dp = new int[length + 1][left + 1];


            // 01背包
            // i(1 ~ len)表示遍历（不一定选）了 i 个元素，j(0 ~ sum) 表示它们的和
            dp[0][0] = 1;
            for (int i = 1; i <= length; i++) {
                for (int j = 0; j <= left; j++) {
                    // 装不下（不选当前元素）
                    if (j - nums[i - 1] < 0) {
                        dp[i][j] = dp[i - 1][j];
                        // 可装可不装（当前元素可选可不选）
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            /**
             * 初始化：0个元素，和为0，情况有1种（因为没有元素，所以只能不选，和为0）：dp[0][0] = 1
             * 不选当前元素，即"部分和"(即j)与之前相同：dp[i][j] = dp[i - 1][j]
             * 可选可不选，不选的情况是2，选当前元素的话则之前的状态应为dp[i - 1][j - num]（这里的num指的是当前元素的值，即代码中的nums[i - 1]），二者相加，即：dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num]
             */

            return dp[length][left];

        }
    }

    class Solution1 {

        public int findTargetSumWays(int[] nums, int S) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 背包容量为整数，sum + S为奇数的话不满足要求
            if (((sum + S) & 1) == 1) {
                return 0;
            }
            // 目标和不可能大于总和
            if (S > sum) {
                return 0;
            }
            sum = (sum + S) >> 1;
            int len = nums.length;
            int[][] dp = new int[len + 1][sum + 1];
            dp[0][0] = 1;

            // 如果迭代部分 j 的初值赋 1 的话，就要先初始化 j = 0 的情况
        /* int count = 1;
        for (int i = 1; i <= len; i++) {
            // ±0 均可
            if (nums[i - 1] == 0) {
                count *= 2;
            }
            dp[i][0] = count;
        } */

            // 01背包
            // i(1 ~ len)表示遍历（不一定选）了 i 个元素，j(0 ~ sum) 表示它们的和
            for (int i = 1; i <= len; i++) {
                for (int j = 0; j <= sum; j++) {
                    // 装不下（不选当前元素）
                    if (j - nums[i - 1] < 0) {
                        dp[i][j] = dp[i - 1][j];
                        // 可装可不装（当前元素可选可不选）
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    }
                }
            }

            return dp[len][sum];
        }

    }

    class Solution2 {
        public int findTargetSumWays(int[] nums, int S) {
            int sum = 0;
            for(int i=0; i<nums.length; i++){
                sum += nums[i];
                nums[i] += nums[i];
            }
            if(S>sum) return 0;
            S += sum;
            int[] dp = new int[S+1];
            dp[0] = 1;
            for(int num: nums){
                for(int i=S; i>=0; i--){
                    if(i-num>=0){
                        dp[i] = dp[i] + dp[i-num];
                    }
                }
            }
            return dp[S];
        }
    }

    class Solution3 {
        /**
         * 查找目标和的方法数
         * 给定一个整数数组 nums 和一个目标整数 target，求出可以通过从数组中选择数字并进行加法运算得到目标和的方法数。
         * 数组中的每个数字可以被选择任意次数。
         *
         * 其中 dp[i][j] 表示在数组 nnums 的前 i 个数中选取元素，使得这些元素之和等于 j 的方案数
         *
         *
         * @param nums 整数数组，包含要进行加法运算的整数
         * @param target 目标整数，要达到的和
         * @return 返回可以通过选择数组中的数字并进行加法运算得到目标和的方法数
         */
        public int findTargetSumWays(int[] nums, int target) {
            // 计算数组 nums 中所有数字的和
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 计算达到目标和需要减去的数
            int diff = sum - target;
            // 如果差值小于0或者差值为奇数，则无法达到目标和，返回0
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            // 计算可以取的负数的个数
            int n = nums.length;
            int neg = diff / 2;
            // 使用动态规划的二维数组，dp[i][j] 表示前 i 个数字中选取和为 j 的方法数
            int[][] dp = new int[n + 1][neg + 1];
            // 初始化，当不选取任何数字时，和为 0 的方法数为 1
            dp[0][0] = 1;
            // 遍历数组 nums，更新 dp 数组
            for (int i = 1; i <= n; i++) {
                int num = nums[i - 1];
                for (int j = 0; j <= neg; j++) {
                    // 不选择当前数字 num
                    dp[i][j] = dp[i - 1][j];
                    // 选择当前数字 num
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                }
            }
            // 返回最后一个数字选取和为 neg 的方法数
            return dp[n][neg];
        }

    }


    @Test
    public void test1()
    {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(new Solution3().findTargetSumWays(nums, target));
    }


}
