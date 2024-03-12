package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/12 16:53
 * @注释
 */
public class BeiBao {

    @Test
    public void main() {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        new Solution().packageProblem(weight, value, bagSize);
    }

    class Solution{

        /**
         *
         *
         * <br/>
         * <img src='https://code-thinking-1253855093.file.myqcloud.com/pics/2021011010314055.png' />
         *
         * @param weights     每个物品的重量
         * @param values      每个物品的价值
         * @param carryNumber 允许携带的数量
         * @return 价值
         */
        public int packageProblem(int[] weights, int[] values, int carryNumber) {
            /**
             * 	重量	价值
             * 物品0	1	15
             * 物品1	3	20
             * 物品2	4	30
             */
            // dp[i][j] i表示携带的产品，j表示容量为j的背包 dp[i][j]为从0-i个产品里取，最大的价值
            // 1. 不放物品i的最大价值，dp[i][j] = dp[i-1][j] 不放物品i，所以i不占重量，所以不用-weight[i]
            // 2. 放物品i的最大价值 dp[i-1][j-weight[i]] + value[i]  如果放了物品i，那最大价值就 不放物品i的最大价值+物品i的价值（j-weight[i] 表示放i需要腾出来空间）
            //          dp[i - 1][j - weight[i]] 为背包容量为j - weight[i]的时候不放物品i的最大价值，注意是不放物品i，所以要减去weight[i]
            // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]]  + value[i]);   不放物品的价值 加上i物品的价值

            // 初始化：如果容量为0，最大价值都为0；如果容量为1、2、3，只能放物品0的时候，最大价值都是15
            int goodsNumber = weights.length;
            int[][] dp = new int[goodsNumber][carryNumber + 1];
            // 把第0个物品进行初始化，前提是能够放入第0个物品
            for (int i = weights[0]; i < carryNumber + 1; i++) {
                dp[0][i] = values[0];
            }

            /**
             * 先遍历物品，再遍历背包
             */
            for (int i = 1; i < goodsNumber; i++) {
                for (int j = 1; j < carryNumber + 1; j++) {
                    if (weights[i] > j) {
                        // 物品i放不进背包容量为j的背包
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        // 能放进去
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                    }
                }
            }

            

            // 打印dp数组
            for (int i = 0; i < goodsNumber; i++) {
                for (int j = 0; j <= carryNumber; j++) {
                    System.out.print(dp[i][j] + "\t");
                }
                System.out.println("\n");
            }

            return dp[weights.length - 1][carryNumber];
        }

    }
}
