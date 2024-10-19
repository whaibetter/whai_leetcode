package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 12:11
 * @注释
 */
public class MaiGuPiao {

    @Test
    public void test() {
        int[] prices = {7,1,5,3,6,4};
        int max = new LeetCode121().new Solution1().maxProfit(prices);
        System.out.println(max);
    }

    class LeetCode121{


        public class Solution {
            public int maxProfit(int[] prices) {
                int max = 0;
                int left = 0;
                int right = 0;
                while (right < prices.length) {
                    if (prices[left] > prices[right]) {
                        left = right;
                    }
                    max = Math.max(max, prices[right] - prices[left]);
                    right++;
                }
                return max;
            }
        }


        public class Solution1 {
            /**
             * dp[i][0] 表示第i天手里咩有股票的最大利润
             * dp[i][1] 表示手里有股票的最大利润
             *      - 前一天就有
             *      - 刚买入，因为只能买卖一次，所以 【0利润-价格】
             *
             * @param prices
             * @return
             */
            public int maxProfit(int[] prices) {
                int[][] dp = new int[prices.length][2];
                dp[0][1] = -prices[0];
                for (int i = 1; i < prices.length; i++) {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                    dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 只能买入卖出一次
                }
                return dp[prices.length - 1][0];
            }
        }

        public class Solution2 {
            /**
             * dp[i][0] 表示第i天手里咩有股票的最大利润
             * dp[i][1] 表示手里有股票的最大利润
             *
             * @param prices
             * @return
             */
            public int maxProfit(int[] prices) {
                int noHaveMaxProfit = 0;
                int haveMaxProfit = -prices[0];
                for (int i = 1; i < prices.length; i++) {
                    // 必须这个顺序，因为需要前一天手里有股票的haveMaxProfit
                    noHaveMaxProfit = Math.max(noHaveMaxProfit, haveMaxProfit + prices[i]);
                    haveMaxProfit = Math.max(haveMaxProfit, -prices[i]);

                }
                return noHaveMaxProfit;
            }
        }

    }


    class LeetCode122{
        public class Solution {
            public int maxProfit(int[] prices) {
                int[][] dp = new int[prices.length][2];
                dp[0][0] = 0;
                dp[0][1] = -prices[0];
                for (int i = 1; i < prices.length; i++) {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                }
                return dp[prices.length - 1][0];
            }
        }

        public class Solution1 {
            public int maxProfit(int[] prices) {
                int noHaveMaxProfit = 0;
                int haveMaxProfit = -prices[0];
                for (int i = 1; i < prices.length; i++) {
                    noHaveMaxProfit = Math.max(noHaveMaxProfit, haveMaxProfit + prices[i]);
                    haveMaxProfit = Math.max(haveMaxProfit, noHaveMaxProfit - prices[i]);
                }
                return noHaveMaxProfit;
            }
        }

        // 只要上涨价
        class Solution2 {
            public int maxProfit(int[] prices) {
                int res = 0;
                for (int i = 1; i < prices.length; i++) {
                    int sub = prices[i] - prices[i - 1];
                    if (sub > 0) {
                        res += sub;
                    }
                }
                return res;
            }
        }
    }


    class LeetCode123{


        public class Solution {
            /**
             *
             * dp[i][1] 表示 第一次手里有股票
             *  - 前一天就有 dp[i-1][1]
             *  - 前一天没有，今天刚刚买入第一只 dp[i-1][0] - prices[i]
             * dp[i][2] 表示 第一次手里没有股票
             *  - 前一天就没有  dp[i-1][2]
             *  - 前一天有第一只，今天刚刚卖出 dp[i-1][1] + prices[i]
             * dp[i][3] 表示 第二次手里有股票
             *  - 前一天就有第二支股票 dp[i-1][3]
             *  - 前一天没有第二支股票，即前一天没有第一支股票，今天刚刚买入 dp[i-1][2] - prices[i]
             * dp[i][4] 表示 第二次手里没股票
             *  - 前一天就没有第二支股票 dp[i-1][4]
             *  - 前一天有第二支股票，今天刚刚卖出 dp[i-1][3] + prices[i]
             * @param prices
             * @return
             */
            public int maxProfit(int[] prices) {
                int[][] dp = new int[prices.length][5];
                dp[0][1] = -prices[0];
                dp[0][2] = 0;
                dp[0][3] = -prices[0];
                dp[0][4] = 0;
                for (int i = 1; i < prices.length; i++) {
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
                    dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
                    dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
                }
                return dp[prices.length - 1][4];
            }
        }
    }

    @Test
    public void test123() {
        // [3,3,5,0,0,3,1,4]
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int max = new LeetCode188().new Solution().maxProfit(2, prices);
        System.out.println(max);
    }


    class LeetCode188{

        class Solution {
            /**
             * has[i][j] 表示有第j个股票的最大利润
             * noHave[i][j] 表示 没有第j个股票的最大利润
             * @param k
             * @param prices
             * @return
             */
            public int maxProfit(int k, int[] prices) {
                int[][] has = new int[prices.length][k + 1];
                int[][] noHave = new int[prices.length][k + 1];

                for (int j = 1; j <= k; j++) {
                    has[0][j] = -prices[0];
                }

                for (int i = 1; i < prices.length; i++) {
                    for (int j = 1; j <= k; j++) {
                        // 有第j个股票
                        //    - 前一天就有第j个
                        //    - 前一天没有，刚刚买入（刚刚买入，要在前一个状态为手里没有第j-1个股票的情况 ）;
                        has[i][j] = Math.max(has[i - 1][j], noHave[i - 1][j - 1] - prices[i]);
                        // 前一天就没有第j个股票，有股票，刚刚卖出
                        noHave[i][j] = Math.max(noHave[i - 1][j], has[i - 1][j] + prices[i]);
                    }
                }
                return noHave[prices.length - 1][k];
            }
        }

    }

    @Test
    public void testchange() {

        int N = 6; // 总天数
        int k = 2; // 最多交易次数

        double M = 10000; // 初始资金
        double[] prices1 = {3, 2, 6, 5, 1, 3}; // 每天的股票价格
        double res = new Change().new StockTrading().maxProfit(k, N, M, prices1);
        System.out.println("最大利润：" + (res - M));
    }

    class Change {

        public class StockTrading {
            public static double maxProfit(int k, int N, double M, double[] prices) {
                if (prices == null || prices.length == 0 || k == 0) {
                    return 0; // 没有交易或者没有股票价格
                }

                // dp[i][j][l]表示第i天交易了j次，l=0表示不持有股票，l=1表示持有股票
                double[][][] dp = new double[N][k + 1][2];

                // 初始化dp数组，第一天不持有股票的利润是M，持有股票的利润是M - prices[0]（假设买入股票）
                for (int j = 0; j <= k; j++) {
                    dp[0][j][0] = M; // 第一天不持有股票
                    dp[0][j][1] = M / prices[0]; // 第一天买入股票
                }

                // 开始动态规划
                for (int i = 1; i < N; i++) {
                    for (int j = 0; j <= k; j++) {
                        // 第i天不持有股票的情况
                        dp[i][j][0] = dp[i-1][j][0]; // 昨天也不持有
                        if (j > 0) {
                            dp[i][j][0] = Math.max(dp[i][j][0], dp[i-1][j][1] * prices[i]); // 今天卖出
                        }

                        // 第i天持有股票的情况
                        dp[i][j][1] = dp[i-1][j][1]; // 昨天也持有
                        if (j > 0) {
                            dp[i][j][1] = Math.max(dp[i][j][1], dp[i-1][j-1][0] / prices[i]); // 今天买入
                        }
                    }
                }

                // 返回最后一天最多交易k次且不持有股票的最大利润
                return dp[N-1][k][0];
            }

            public static void main(String[] args) {
                int N = 6; // 总天数
                int k = 2; // 最多交易次数
                double M = 10000; // 初始资金
                double[] prices = {3, 2, 6, 5, 1, 3}; // 每天的股票价格

                System.out.println("最大利润：" + (maxProfit(k, N, M, prices) - M));
            }
        }


        public class StockTrading1 {
            // 计算最大利润的方法
            public static double maxProfit(double M, int K, double[] prices) {
                int N = prices.length; // 获取天数
                double[][][] dp = new double[N + 1][K + 1][2]; // 动态规划表

                // 初始化基本情况
                for (int i = 0; i <= N; i++) {
                    for (int k = 0; k <= K; k++) {
                        dp[i][k][0] = 0.0; // 手中没有股票
                        dp[i][k][1] = -Double.MAX_VALUE; // 不可能的状态
                    }
                }

                dp[0][0][0] = M; // 初始资金为M，未进行任何交易

                // 填充动态规划表
                for (int i = 1; i <= N; i++) {
                    for (int k = 1; k <= K; k++) {
                        // 今天手中没有股票
                        dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);

                        // 今天手中持有股票
                        dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i - 1]);
                    }
                }

                // 返回在最多K次交易且手中没有股票时的最大利润
                return dp[N][K][0];
            }

        }



    }

    @Test
    public void testchange1() {

        int N = 6; // 总天数
        int k = 2; // 最多交易次数

        double M = 10000; // 初始资金
        double[] prices1 = {3, 2, 6, 5, 1, 3}; // 每天的股票价格
        double res = new Change().new StockTrading1().maxProfit(M, k, prices1);
        System.out.println("最大利润：" + (res - M));
    }

    @Test
    public void test309() {
        int[] prices = {1, 2, 3, 0, 2};
        int max = new LeetCode309().new Solution().maxProfit(prices);
        System.out.println(max);
    }

   class LeetCode309{

       class Solution {
           /**
            * 冷冻
            * @param prices
            * @return
            */
           public int maxProfit(int[] prices) {
               if (prices.length < 2) {
                    return 0;
               }
               int[][] dp = new int[prices.length][2];
               dp[0][0] = 0;
               dp[0][1] = -prices[0];
               dp[1][0] = Math.max(0, prices[1] - prices[0]); // 第一天手里没有股票，可能前一天买了，今天卖了
               dp[1][1] = Math.max(dp[0][1], -prices[1]);// 第1天手里有股票，前一天有，前一天没有，今天买入
               for (int i = 2; i < prices.length; i++) {
                   dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                   // 没有，前一天就没有，今天刚刚卖出
                   dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
                   // 有，前一天就有，今天刚刚买入（前一天必须不能买）
               }
               return dp[prices.length - 1][0];
           }
       }
   }

   class LeetCode714{
       class Solution {
           public int maxProfit(int[] prices, int fee) {
               int[][] dp = new int[prices.length][2];
               dp[0][1] = -prices[0];
               for (int i = 1; i < prices.length; i++) {
                   dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee); // 每次卖出的手续费
                   dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
               }
               return dp[prices.length - 1][0];
           }
       }

       class Solution1 {
           public int maxProfit(int[] prices, int fee) {
               // 注意买入的时候就付了手续费
               int buy = prices[0] + fee; // 最低买入价钱
               int profit = 0;
               for (int i = 1; i < prices.length; i++) {
                   if (prices[i] + fee < buy) {
                       // 更新更便宜的买入价钱
                       buy = prices[i] + fee;
                   } else if (prices[i] > buy) {
                       // 当天价格大于买入价格，直接买入，因为prices[i] + fee < buy存在，所以这里一定是获利的
                       profit += (prices[i] - buy);
                       buy = prices[i]; // 更新买入价钱
                   }
               }
               return profit;
           }
       }
   }

   @Test
    public void test714() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int max = new LeetCode714().new Solution().maxProfit(prices, fee);
        System.out.println(max);
   }

}
