package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/26 13:30
 * @注释
 */
public class AbsoluteBeiBao {

    @Test
    public void test() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int capacity = 4;
        System.out.println(absoluteBeiBaoOneD(weight, value, capacity));
    }

    /**
     * 使用动态规划解决背包问题，求解在给定背包容量下，能够获得的最大价值。
     * @param weight 物品的重量数组
     * @param value 物品的价值数组
     * @param capacity 背包的容量
     */
    public int absoluteBeiBao(int[] weight, int[] value,int capacity) {

        int length = weight.length;
        int[][] dp = new int[length + 1][capacity + 1];

        // 初始化动态规划数组
        // dp[i][j] 表示在前 i 个物品【0-(i-1)】中，背包容量为 j 的情况下的最大价值。
        for (int i = 1; i <= length; i++) { //
            for (int j = 1; j <= capacity; j++) {
                if (j >= weight[i-1]) {
                    // 当前物品重量小于等于背包容量时，考虑放入当前物品
                    // 完全背包二维数组的代码跟一维只有下面一个下标不同，
                    // 那就是“放i”这个选择，因为是可以重复放的，所以是dp[i]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i-1]] + value[i-1]);
                }else {
                    // 当前物品重量大于背包容量，无法放入，维持前一个状态
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[length][capacity];
    }

    public int absoluteBeiBaoOneD(int[] weight, int[] value,int capacity) {

        int length = weight.length;
        int[] dp = new int[capacity + 1];

        // 初始化动态规划数组
        // dp[i][j] 表示在前 i 个物品【0-(i-1)】中，背包容量为 j 的情况下的最大价值。
        // dp[j] 表示前i个物品中，背包容量为j的最大价值
        for (int i = 1; i <= length; i++) { //
            for (int j = weight[i - 1]; j <= capacity; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
            }
        }
        return dp[capacity];
    }



}
