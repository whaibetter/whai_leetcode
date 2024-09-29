package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/27 11:01
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        System.out.println(new Solution().jump(new int[]{2,3,1,1,4}));
    }

    class Solution {
        /**
         * 每个区间内只有一个最跳数
         * 每个区间只增加一次jump
         * 1. 标记临时最大区间
         * 2. 标记区间结束时jump++
         * @param nums
         * @return
         */
        public int jump(int[] nums) {

            // 最远覆盖范围能否到达nums.length-1

            if (nums.length == 1) {
                return 0;
            }

            // 最大覆盖范围
            int maxCover = 0;

            // 当前index覆盖范围区间
            int curInterval = 0;
            int jumpCount = 0;
            for (int i = 0; i < nums.length; i++) {
                // 当前最大覆盖区间
                maxCover = Math.max(maxCover, i + nums[i]);

                // 必须等待区间增加后再判断是否到末位
                if (maxCover >= nums.length - 1) {
                    jumpCount++;
                    break;
                }

                // 如果到了当前index覆盖的区间最后一个
                if (i ==  curInterval) {
                    jumpCount++;
                    curInterval = maxCover;

                }


            }
            return jumpCount;
        }
    }

    class Solution1 {
        /**
         * 每个区间内只有一个最跳数
         * 每个区间只增加一次jump
         * 1. 标记临时最大区间
         * 2. 到达最大跳数++
         * @param nums
         * @return
         */
        public int jump(int[] nums) {

            // 最远覆盖范围能否到达nums.length-1

            if (nums.length == 1) {
                return 0;
            }

            // 最大覆盖范围
            int maxCover = 0;

            // 当前index覆盖范围区间
            int curInterval = 0;
            int jumpCount = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                // if (nextDistance >= nums.size() - 1) break;
                // 让nums.length变成num.length-1 本质是一样的

                // 当前最大覆盖区间
                maxCover = Math.max(maxCover, i + nums[i]);
                if (i == curInterval) {
                    jumpCount++;
                    curInterval = maxCover;
                }
            }
            return jumpCount;
        }
    }

}
