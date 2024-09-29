package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.*;
import java.util.function.ToIntFunction;

/**
 * 349. 两个数组的交集

 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class LeetCode349 {

    @Test
    public void test() {
        int[] nums1 = {9,4,9,8,4};
        int[] nums2 = {4,9,5};
        int[] res = new Solution1().intersection(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }



    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {

            HashSet<Integer> target = new HashSet<>();
            HashSet<Integer> res = new HashSet<>();
            for (int i : nums1) {
                target.add(i);
            }
            for (int i : nums2) {
                if (target.contains(i)) {
                    res.add(i);
                }
            }

            return res.stream().mapToInt(new ToIntFunction<Integer>() {
                @Override
                public int applyAsInt(Integer value) {
                    return value;
                }
            }).toArray();
        }
    }

    class Solution1 {
        public int[] intersection(int[] nums1, int[] nums2) {

            int[] A = new int[1001];
            int[] B = new int[1001];

            for (int i : nums1) {
                A[i]++;
            }
            for (int i : nums2) {
                B[i]++;
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                if (A[i] != 0 && B[i] != 0) {
                    res.add(i);
                }
            }

            return res.stream().mapToInt(i -> i).toArray();

        }
    }
}
