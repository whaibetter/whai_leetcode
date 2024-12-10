package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/28 16:59
 * @注释
 */
public class LeetCode2602 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {3,1,6,8};
        int[] queries = {1,5};
        List<Long> res = solution.minOperations(nums, queries);
        res.forEach(System.out::println);
    }


    /**
     * 超时
     */
    class Solution {
        public List<Long> minOperations(int[] nums, int[] queries) {
            List<Long> res = new ArrayList<>();
            for (int query : queries) {
                res.add(target(nums, query));
            }
            return res;
        }

        public long target(int[] nums, int target) {

            long res = 0;
            for (int num : nums) {
                res += Math.abs(target - num);
            }
            return res;
        }
    }

    class Solution1 {
        public List<Long> minOperations(int[] nums, int[] queries) {
            List<Long> res = new ArrayList<>();
            Arrays.sort(nums);
            // 排序后，找到对应的元素，左边小于，右边大于
            // 求前缀和
            int length = nums.length;
            long[] preSum = new long[length + 1];
            for (int i = 0; i < length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }

            for (int target : queries) {
                int index = findIndex(nums, target);
                long left = (long) index * target - preSum[index];
                // index 为横坐标 target为纵坐标高度 preSum为index位置前面的总和
                long right = preSum[length] - preSum[index] - (long) target * (length - index);
                res.add(left + right);
            }

            // 一侧的前缀和与 “平均值*一侧区间长度” 就代表这一侧的总操作数

            return res;
        }


        public int findIndex(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }

    }

    @Test
    public void test1()
    {
        Solution2 solution2 = new Solution2();
        int[] nums = {3,1,6,8};
        int[] queries = {5};
        List<Long> res = solution2.minOperations(nums, queries);
        res.forEach(System.out::println);
    }


    class Solution2 {
        public List<Long> minOperations(int[] nums, int[] queries) {
            Arrays.sort(nums);

            // 计算前缀和
            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

            List<Long> res = new ArrayList<>();
            for (int query : queries) {
                res.add(getOperation(nums, query, preSum));
            }
            return res;

        }

        /**
         * <img src="http://so9vd9h8j.hd-bkt.clouddn.com/1679808210-FVsAou-t3.png">
         *     </img>
         * @param nums 原数组
         * @param target 目标
         * @param preSum 前缀和
         * @return
         */
        public Long getOperation(int[] nums, int target,long[] preSum) {

            int index = findIndex(nums, target);
            // index左边全部比他小、右边全部比他大
            // 0 - index 为小于他的数字个数 index-length为大于他的个数
            // 小于他的数的总和 = 小于的个数 index * (target-nums[0]) - index位置的前缀和（前缀和就是一个下三角形的面积）
            long leftIncr = (long) index * target - preSum[index];
            long rightIncr =
                    preSum[nums.length] - preSum[index]
                            - (long) target * (nums.length - index);

            return leftIncr + rightIncr;
        }

        /**
         * 二分查找
         * @param nums 有序数组
         * @param target
         * @return
         */
        public int findIndex(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid =  (right + left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

    }
}
