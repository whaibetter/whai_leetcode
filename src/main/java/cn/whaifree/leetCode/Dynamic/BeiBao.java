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
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
//
//        new Thread(() -> {
//
//        }).start();
//
//
//        String a = "a";
//
//        String b = new String("a");
//
//        System.out.println(a == b);
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        new Solution2().packageProblem(weight, value, bagSize);
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


    class Solution1{

        /**
         * dp[i][j] i表示商品，j表示空间 dp[i][j]表示 容量为j为包裹，从0-i个商品中取得最大值
         *
         * 初始化
         *      0 1 2 3 4 5  包容量
         * 物品0 0 0 2 2 2 2
         * 物品1 0
         * 物品2 0
         *
         * dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
         *
         * @param weights 物品的重量
         * @param values  物品的价值
         * @param carryNumber  可以携带的数量
         * @return
         */
        public int packageProblem(int[] weights, int[] values, int carryNumber) {
            int length = weights.length;
            int[][] dp = new int[length][carryNumber + 1];
            for (int i = 0; i < length; i++) {
                dp[i][0] = 0;
            }
            for (int i = weights[0]; i <= carryNumber; i++) {
                dp[0][i] = values[0];
            }

            // 如果i 不放东西 dp[i][j] = dp[i-1][j]
            // 如果i 放东西 dp[i][j] = max(dp[i-1][j],dp[i-1][j-weight[i]]-value[i])

            // i为商品；j为容量。
            for (int i = 1; i < length; i++) {
                for (int j = 1; j <= carryNumber; j++) {
                    if (weights[i] > j) {
                        // 重量太大，放不进去
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                    }
                }
            }


            return dp[length - 1][carryNumber];

        }
    }

    class Solution2{

        /**
         *
         * dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
         * dp[j] 表示容量为j的背包的最大价值总和
         *
         * 初始化
         *      0 1 2 3 4 5  包容量
         * 物品0 0 0 2 2 2 2
         * 物品1 0
         * 物品2 0
         *
         * 不放这个商品 那价值不变，还是dp[j]
         * dp[j] = max(dp[j] , dp[j-weight[i]]+value[i]])
         *
         * @param weights 物品的重量
         * @param values  物品的价值
         * @param carryNumber  可以携带的数量
         * @return
         */
        public int packageProblem(int[] weights, int[] values, int carryNumber) {
            int length = weights.length;
            int[] dp = new int[carryNumber + 1];

            // 不放这个商品 那价值不变，还是dp[j]
            // dp[j] = max(dp[j] , dp[j-weight[i]]+value[i]])

            // i为商品；j为容量。
            for(int i = 0; i < length; i++) { // 遍历物品
                for(int j = carryNumber ;j >= weights[i]; j--) { // 遍历背包容量
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
                /**
                 * 倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
                 *
                 * 举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15
                 * 如果正序遍历
                 * dp[1] = dp[1 - weight[0]] + value[0] = 15
                 * dp[2] = dp[2 - weight[0]] + value[0] = 30
                 *
                 * 此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。
                 */
            }


            return dp[carryNumber];

        }
    }

}
