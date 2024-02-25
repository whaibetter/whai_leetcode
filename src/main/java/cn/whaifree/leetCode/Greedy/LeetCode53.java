package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/25 10:53
 * @注释
 */
public class LeetCode53 {
    @Test
    public void test() {
        System.out.println(new LeetCode53().new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    class Solution {
        /**
         * 遇到加上变为负数，重新从0计算。
         * 因为前面那一串会拖累后面那串
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                // 加上某个数后是否会变为负数，会则直接从0开始计算，因为其只会拖累后面的串
                sum += nums[i];
                maxSum = Math.max(sum, maxSum);
                if (sum  < 0) {
                    sum = 0;
                }
            }


            return maxSum;
        }
    }


}

