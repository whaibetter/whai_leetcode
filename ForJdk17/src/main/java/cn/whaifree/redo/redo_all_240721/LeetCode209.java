package cn.whaifree.redo.redo_all_240721;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/21 19:52
 * @注释
 */
public class LeetCode209 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen2(target,nums));
    }

    /**
     * 子数组
     * 双指针
     *
     * - right 向前跑
     * - left 计算是否超过target
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int tmpSum = 0;
        while (right < nums.length) {
            while (left < right && tmpSum >= target) {
                minLength = Math.min(minLength, right - left);

                tmpSum -= nums[left];
                left++;
            }
            tmpSum += nums[right];
            right++;
        }

        while (tmpSum >= target) {
            minLength = Math.min(minLength, right - left);
            tmpSum -= nums[left];
            left++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    /**
     * 前缀和
     *
     *   2,3,1,2,4,3 纵坐标
     *   0 1 2 3 4 5 横坐标
     * 0 2 5 6 8 12 15 // 面积
     *
     * <img src="http://so9vd9h8j.hd-bkt.clouddn.com/image-20240329122057505.png">
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i < prefixSum.length; i++) {
            // preSum + target = fill
            int fill = prefixSum[i] + target;
            int end = Arrays.binarySearch(prefixSum, fill);
            if (end < 0) {
                end = -end - 1;
            }

            if (end < prefixSum.length) {
                minLength = Math.min(minLength, end - i);
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
