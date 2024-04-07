package cn.whaifree.redo.redo_24_4_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 11:12
 * @注释
 */
public class LeetCode2602 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {2,9,6,3};
        int[] queries = {10};
        List<Long> res = solution.minOperations(nums, queries);
        System.out.println(res);
    }


//    class Solution {
//        public List<Long> minOperations(int[] nums, int[] queries) {
//
//            List<Long> res = new ArrayList<>();
//            for (int query : queries) {
//                opr(nums, query, res);
//            }
//            return res;
//        }
//
//        public void opr(int[] nums, int query, List<Long> res) {
//            long count = 0;
//            for (int num : nums) {
//                count += Math.abs(num - query);
//            }
//            res.add(count);
//        }
//    }

    class Solution {
        public List<Long> minOperations(int[] nums, int[] queries) {
            Arrays.sort(nums);
            // 计算前缀和
            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }


            List<Long> res = new ArrayList<>();
            for (int query : queries) {
                opr(preSum,nums, query, res);
            }
            return res;
        }

        public void opr(long[] preSum, int[] nums, int query, List<Long> res) {
            int index = Arrays.binarySearch(nums, query);
            if (index < 0) {
                index = -index - 1;
            }
            long left = (long) index * query - preSum[index];
            long right = preSum[preSum.length - 1] - preSum[index] - (long) (preSum.length - index - 1) * query;
            res.add(left + right);
        }
    }

}
