package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/15 14:51
 * @注释
 */
public class LeetCode698 {

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(new Solution().canPartitionKSubsets(nums, k));
    }

    class Solution {

        public int[] numUsed;
        public boolean canPartitionKSubsets(int[] nums, int k) {
            numUsed = new int[nums.length];
            Arrays.sort(nums);
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0 || nums[nums.length - 1] > sum / k)
                return false;

            return divideGroups(nums, nums.length - 1, sum / k, 0, k);
        }

        /**
         *
         * @param nums
         * @param start
         * @param target
         * @param current
         * @param k
         * @return
         */
        public boolean divideGroups(int[] nums, int start, int target, int current, int k) {
            if (k == 1)
                return true; // 分组操作执行k-1次之后，最后剩余的元素，就是最后一组了，不需要再匹配
            if (current == target)
                return divideGroups(nums, nums.length - 1, target, 0, k - 1); // 分组操作执行k-1次后，最后剩余的元素，就是最后一组了，不需要再匹配
            for (int i = start; i >= 0; i--) {
                if (numUsed[i] == 1 || current + nums[i] > target) continue; // 被使用的元素，不能再次使用；总和大于目标值，也不能使用


                numUsed[i] = 1; // 标记占用
                if (divideGroups(nums, i - 1, target, current + nums[i], k)) return true;
                numUsed[i] = 0; // 撤销标记


                while (i > 0 && nums[i - 1] == nums[i]) i--; // 例如“12333333...”，假如最右侧的“3”这个值没有匹配上，那么它左侧的剩余五个“3”都不需要再匹配了。
            }
            return false;
        }
    }


}
