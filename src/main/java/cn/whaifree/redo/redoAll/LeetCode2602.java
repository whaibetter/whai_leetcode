package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/22 23:09
 * @注释
 */
public class LeetCode2602 {

    class Solution {
        public List<Long> minOperations(int[] nums, int[] queries) {
            List<Long> res = new ArrayList<>();
            for (int query : queries) {
                res.add(minOpr(nums, query));
            }
            return res;
        }

        public long minOpr(int[] nums, long q) {
            long res = 0;
            for (int num : nums) {
                res += Math.abs(num - q);
            }
            return res;
        }
    }

    @Test
    public void test() {

       // [3,1,6,8], queries = [1,5]
        int[] nums = new int[]{3, 1, 6, 8};
        int[] queries = new int[]{1, 5};
        Solution1 solution = new Solution1();
        List<Long> res = solution.minOperations(nums, queries);
        System.out.println(res);
    }

    class Solution1 {
        public List<Long> minOperations(int[] nums, int[] queries) {
            Arrays.sort(nums);

            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }



            List<Long> res = new ArrayList<>();
            for (int query : queries) {
                res.add(minOpr(preSum, nums, query));
            }
            return res;
        }

        public long minOpr(long[] preSum,int[] nums, int q) {
            int index = Arrays.binarySearch(nums, q);
            if (index < 0) {
                // index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). The
                index = -index - 1;
            }

            long preSize = preSum[index];
            long then = (long) q * index - preSize;
            long square = (long)(preSum.length - index - 1) * q;
            long up = preSum[preSum.length - 1] - preSize - square;

            return then + up;
        }



    }
}
