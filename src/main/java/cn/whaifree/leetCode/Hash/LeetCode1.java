package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
public class LeetCode1 {

    @Test
    public void test() {
        int[] ints = new Solution().twoSum(new int[]{1, 2, 3, 4}, 4);
        for (int anInt : ints) {
            System.out.println(anInt);

        }
    }

    class Solution {
        /**
         * 时间复杂度 O(N) map.containsKey(key)的时间复杂度是O(1)，因为Map的containsKey方法使用哈希表实现，可以在常数时间内判断Map中是否包含指定的键。
         * 空间复杂度 O(N)
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int key = target - nums[i];
                if (map.containsKey(key)) {
                    return new int[]{i, map.get(key)};
                }else {
                    map.put(nums[i], i);
                }
            }
            return null;
        }
    }
}
