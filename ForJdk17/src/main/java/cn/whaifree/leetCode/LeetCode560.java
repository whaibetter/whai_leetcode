package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 15:17
 * @注释
 */
public class LeetCode560 {

    @Test
    public void test() {
        int[] nums = {-1, -1, 1};
        int k = 0;

        int res = new Solution().subarraySum(nums, k);
        System.out.println(res);
    }

    class Solution {
        /**
         * -2 -5 1 2
         *
         * -2 -7 -6 -4
         * 子数组(连续)的个数 。
         *
         * 前缀和，前缀和的区间总面积为k的个数
         *
         * @param nums
         * @param k
         * @return
         */
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;

            int[] preSum = new int[len + 1];
            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }


            int count = 0;
            for (int left = 0; left < len; left++) {
                for (int right = left; right < len; right++) {
                    // 区间和 [left..right]，注意下标偏移
                    if (preSum[right + 1] - preSum[left] == k) {
                        count++;
                    }
                }
            }
            return count;

        }

        /**
         * 子数组(连续)的个数 。
         *
         * 前缀和，前缀和的区间总面积为k的个数
         *
         *
         * 1. 每次计算的元素n，在前面计算好前缀和，只要找到k-n的前缀和的个数
         * --- 计算每个前缀和的数量，
         *
         *
         * @param nums
         * @param k
         * @return
         */
        public int subarraySum1(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            // map为各种前缀和的数量
            int preSum = 0;
            int res = 0;
            for (int num : nums) {
                preSum += num;
                // 画图，就是一个大的面积，找到小的面积，取其中的差值区域；即找到一个范围==k
                // | preSum-k  | k  |
                // 整个是preSum
                // PreSum当前的前缀和-某个之前的前缀和=k，而map存储之前前缀和k的个数为v个
                if (map.containsKey(preSum - k)) {
                    res += map.get(preSum - k);
                }
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            }

            return res;

        }
    }
}
