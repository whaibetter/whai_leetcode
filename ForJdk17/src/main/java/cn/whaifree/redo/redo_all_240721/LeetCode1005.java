package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/5 23:37
 * @注释
 */
public class LeetCode1005 {
    @Test
    public void test()
    {
        int[] nums = new int[]{-8,3,-5,-3,-5,-2};
        Solution solution = new Solution();
        System.out.println(solution.largestSumAfterKNegations(nums, 6));

    }
    class Solution {
        /**
         *  -4 3 2 1
         *  // 如果绝对值最大的是负数-1
         *  // 把所有负数变为正
         *  // 如果k还有
         *  // 偶数，不改了
         *  // 奇数，找到最小的变为负数
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations(int[] nums, int k) {

            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int absCompare = Math.abs(o2) - Math.abs(o1);
                    if (absCompare != 0) {
                        return absCompare;
                    } else {
                        // 如果绝对值相同，负数排在正数前面
                        return o1 - o2;
                    }
                }
            });

            for (int num : nums) {
                queue.add(num);
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(queue.poll());
            }

            int sum = 0;
            for (int i = 0 ; i < list.size() ; i++) {
                Integer poll = list.get(i);
                if (poll < 0 && k > 0) {
                    sum += -poll;
                    k--;
                }else {
                    sum += poll;
                }
            }
            if (k % 2 == 0) {
                return sum;
            }else {
                Integer poll = Math.abs(list.get(list.size() - 1));
                return sum - poll - poll;
            }

        }
    }

    class Solution1 {
        /**
         *  -4 -7 3 2 1
         *  -7 -4 1 2 3
         *
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 0;
            int minAbs = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0 && k > 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                minAbs = Math.min(minAbs, nums[i]); // 已经全部翻转
                res += nums[i];
            }

            if (k % 2 == 0) {
                return res;
            }else {
                return res - 2 * minAbs;
            }
        }
    }

}
