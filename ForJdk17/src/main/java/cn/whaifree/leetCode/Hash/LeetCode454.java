package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * 示例 2：
 *
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 *
 *
 *   提示：
 *
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class LeetCode454 {
    @Test
    public void test() {
        //[1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
        System.out.println(new Solution().fourSumCount(
                new int[]{1, 2, 3},
                new int[]{-1, -2, -3},
                new int[]{-1, 2, -3},
                new int[]{0, 2, 3}
        ));

    }


    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (map.get(nums1[i] + nums2[j]) == null) {
                        map.put(nums1[i] + nums2[j], 1);
                    } else {
                        map.put(nums1[i] + nums2[j], map.get(nums1[i] + nums2[j])+1);
                    }
                }
            }
            map.forEach(
                    (k, v) -> System.out.println(k + "=" + v)
            );

            int res = 0;
            for (int i = 0; i < nums3.length; i++) {
                for (int j = 0; j < nums4.length; j++) {
                    int integer = nums3[i] + nums4[j];
                    if (map.containsKey(-integer)) {
                        // 表示前两个数 和后两个数相加为0，即 nums1[i] + nums2[j] = -（nums3[i] + nums4[j]）
                        res += map.get(-integer);
                    }
                }
            }
            return res;
        }
    }

    class Solution1 {
        // 时间复杂度O(N2)
        // 空间复杂度O(N) 在最坏的情况下，A[i]+B[j] 的值均不相同，因此也就需要 O(n^2) 的空间。
        //1
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i : nums1) {
                for (int j : nums2) {
                    int sum = i + j;
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                    // getOrDefault 如果没有则用默认值。就不用使用Contain了
                }
            }

            int res = 0;
            for (int k : nums3) {
                for (int i : nums4) {
                    int integer = k + i;
                    res += map.getOrDefault(- integer, 0);
                }
            }
            return res;
        }
    }
}
