package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 16:21
 * @注释
 */
public class LeetCode740 {
    @Test
    public void test() {
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        int res = new Solution().deleteAndEarn(nums);
        System.out.println(res);
    }

    class Solution {
        /**
         *
         * 打家劫舍

         * @param nums
         * @return
         */
        public int deleteAndEarn(int[] nums) {

            int max = Arrays.stream(nums).max().getAsInt();

            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            int[] sum = new int[max + 1]; // sum[i]表示选择i进行打劫的收益
            // 每个数字出现的总点数，能获取的总收益
            map.forEach(
                    (k, v) -> sum[k] = v * k
            );

            int[] dp = new int[max + 1];
            dp[0] = 0;
            dp[1] = sum[1];
            for (int i = 2; i <= max; i++) {
                // dp[i-1] 不抢，抢 dp[i-2] + 本节点
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + sum[i]);
            }
            return dp[max];
        }
    }
}
