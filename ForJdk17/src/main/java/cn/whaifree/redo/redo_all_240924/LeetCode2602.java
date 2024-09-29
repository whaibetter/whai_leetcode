package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 14:35
 * @注释
 */
public class LeetCode2602 {

    @Test
    public void test() {
        int[] nums = {3,1,6,8};
        int[] queries = {1,3};
        List<Long> res = new Solution().minOperations(nums, queries);
        System.out.println(res);
    }

    class Solution {

        public List<Long> res = null;
        public List<Long> minOperations(int[] nums, int[] queries) {
            Arrays.sort(nums);
            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }


            res = new ArrayList<>();
            for (int query : queries) {
                res.add(opr(preSum, nums, query));
            }
            return res;
        }

        public Long opr(long[] pre, int[] nums, int query) {

            int index = Arrays.binarySearch(nums, query);
            if (index < 0) {
                index = -index - 1;
            }

            long a = pre[index]; // index的高
            long ACircle = (long) (index) * query - a;

            long square = (long) (pre.length - index - 1) * query;

            long BCircle = (pre[pre.length - 1] - a) - square;
            return ACircle + BCircle;
        }


    }
}
