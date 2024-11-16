package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/16 17:01
 * @注释
 */
public class LeetCode2602 {

    @Test
    public void test() {
        int[] nums = {3,1,6,8};
        int[] queries = {1,5};
        Solution solution = new Solution();
        System.out.println(solution.minOperations(nums, queries));
    }

    class Solution {
        public List<Long> minOperations(int[] nums, int[] queries) {
            // 1 3 6 8
            // 1 4 10 18
            Arrays.sort(nums);
            // 前缀和
            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            List<Long> list = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                long l = minOpr(preSum, nums, queries[i]);
                list.add(l);
            }
            return list;
        }


        public long minOpr(long[] preSum, int[] nums, int queries) {
            int index = Arrays.binarySearch(nums, queries);
            if (index < 0) {
                index = -index - 1;
            }

            // 前面的次数
            long preSize = preSum[index];
            long before = (long) index * queries - preSize;
            long afterSize = preSum[nums.length] - preSize;
            long after = afterSize - (long) (nums.length - index) * queries;

            return (before + after);
        }
    }
}
