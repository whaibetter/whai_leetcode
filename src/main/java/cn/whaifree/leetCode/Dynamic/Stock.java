package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 *
 * 买卖股票的最佳时机 四题+变形
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/12 11:46
 * @注释
 */
public class Stock {

    @Test
    public void test()
    {
//        int[] prices = {7,1,5,3,6,4};
        int[] prices = {1,2,3,4,5};
        System.out.println(new LeetCode123_.Solution().maxProfit(prices));
    }
}

class LeetCode188_{

    class Solution {
        public int maxProfit(int k, int[] prices) {
            // dp[i][j] 表示第i天交易第k次的最大收益
            //  dp[i][0] 表示第i天第 0/2 +1 次持有的最大手头
            //  dp[i][1] 表示第i天第 1/2 +1次未持有的最大手头

            int[][] dp = new int[prices.length][k * 2];
            // 未持有 初始化
            for (int i = 0; i < prices.length; i += 2) {
                dp[i][0] = -prices[0];
            }
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < k * 2; j++) {

                }
            }
            return 1;
        }
    }
}

class LeetCode123_ {

    static class Solution {
        /**
         * 最多可以完成 两笔 交易。
         * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
         *
         * dp[i][j] 表示第i天 状态j的最大手头有的钱
         * 一天一共就有五个状态，
         * 0. 没有操作 （其实我们也可以不设置这个状态）
         *
         * 1. 第一次持有股票（第i天持有第一次股票）
         *         - 第i-1天就持有这个第一次股票  dp[i][1] = dp[i-1][1]
         *         - 第i天买入这个股票 dp[i][1] = dp[i-1][0] - prices[i]
         * 2. 第一次不持有股票
         *         - 第i天卖出 dp[i][2] = dp[i-1][1]+price[i]
         *         - 第i-1天就不持有第一支 dp[i][2] = dp[i-1][2]
         * 3. 第二次持有股票
         *         - 第i天买入第二支 dp[i][3] = dp[i-1][2] - price[i]
         *         - i-1天就持有 dp[i][3] = dp[i-1][3]
         * 4. 第二次不持有股票
         *         - 第i天卖出第二支 dp[i][4] = dp[i-1][3] + price[i]
         *         - 第i-1天就没有 dp[i][4] = dp[i-1][4]
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][5];
            dp[0][1] = -prices[0];
            dp[0][3] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                // 没有操作，手上的现金自然就是0
                // dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
                dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
                dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
            }
            return dp[prices.length - 1][4];
        }

        public int maxProfit1(int[] prices) {
            int[] dp = new int[5];
            dp[1] = -prices[0];
            dp[3] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[1] = Math.max(dp[1], dp[0] - prices[i]);
                dp[2] = Math.max(dp[2], dp[1] + prices[i]);
                dp[3] = Math.max(dp[3], dp[2] - prices[i]);
                dp[4] = Math.max(dp[4], dp[3] + prices[i]);
            }
            return dp[4];
        }
    }

}

class LeetCode122_{

    static class Solution {
        public int maxProfit(int[] prices) {
            // 只要递增就买入

            int left = 0;
            int right = 1;
            int profit = 0;
            while (right < prices.length) {
                while (left < right && prices[left] > prices[right]) {
                    left++;
                }
                if (prices[left] < prices[right]) {
                    profit += (prices[right] - prices[left]);
                    left = right;
                }
                right++;
            }
            return profit;
        }

    }

    static class Solution1 {
        public int maxProfit(int[] prices) {
            /**
             * dp
             * dp[i][0] 表示 i天没有股票的时候 手头的现金
             *      - i-1就没有 dp[i-1][0]
             *      - i天刚刚卖 dp[i-1][1]+price[i]
             * dp[i][1] 表示 i天有股票的时候 手头的现金
             *      - i-1就有 dp[i-1][1]
             *      - 刚刚买入 dp[i-1][0] - price[i]
             */
            int[][] dp = new int[prices.length][2];
            dp[0][1] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
}

class LeetCode121_ {
    static class Solution {
        public int maxProfit(int[] prices) {
            // 最低点买入，最高点卖出
            int min = Integer.MAX_VALUE;
            int res = 0;
            for (int price : prices) {
                min = Math.min(min, price);
                res = Math.max(res, price - min);
            }
            return res;
        }
    }


    static class Solution2 {
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][2];
            // 本题只能交易一次
            // dp[i][0] 表示第i天手里没有股票的 手里的现金
            //          - 第i天卖出 dp[i][0]=dp[i-1][1]+prices[i]
            //          - 第i-1天就没有股票 dp[i][0]=dp[i-1][0]
            // dp[i][1] 表示第i天手里有股票的 手里的现金
            //          - 第i天买入 dp[i][1] =  - prices[i]
            //          - 第i-1天就有 dp[i][1] = dp[i-1][1]
            dp[0][1] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
            }

            return dp[prices.length - 1][0];
        }
    }

    static class Solution3 {
        public int maxProfit(int[] prices) {
            int[] dp = new int[2];
            // 本题只能交易一次
            // dp[i][0] 表示第i天手里没有股票的 手里的现金
            //          - 第i天卖出 dp[i][0]=dp[i-1][1]+prices[i]
            //          - 第i-1天就没有股票 dp[i][0]=dp[i-1][0]
            // dp[i][1] 表示第i天手里有股票的 手里的现金
            //          - 第i天买入 dp[i][1] =  - prices[i]
            //          - 第i-1天就有 dp[i][1] = dp[i-1][1]
            dp[1] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[0] = Math.max(dp[0], dp[1] + prices[i]);
                dp[1] = Math.max(dp[1], - prices[i]);
            }

            return dp[0];
        }
    }
}

