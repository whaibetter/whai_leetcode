package cn.whaifree.LCR;

import cn.whaifree.interview.Meituan.Test02;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 20:05
 * @注释
 */
public class LCR089 {

    @Test
    public void test() {
        int[] values = {2, 7, 9, 3, 1};
        int result = new Solution().rob(values);
        System.out.println(result);

        // 类似题目
        new Test02().sugar(values);
    }

    /**
     *
     */
    class Solution {
        public int rob(int[] values) {
            if (values.length == 1) {
                return values[0];
            }

            // dp[i]表示当前最大价值
            // dp[i]=max(dp[i-1],dp[i-2]+values[i])
            /**
             *
             * 2,7,9,3,1
             * 2 7 11 11 12
             *
             */

            // 初始化前3个
            int[] dp = new int[values.length];
            dp[0] = values[0];
            dp[1] = Math.max(values[0], values[1]);

            for (int i = 2; i < dp.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + values[i]);
            }

            return dp[values.length - 1];
        }
    }
}
