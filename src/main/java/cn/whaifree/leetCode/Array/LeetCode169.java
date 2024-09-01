package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 2:21
 * @注释
 */
public class LeetCode169
{
    @Test
    public void test()
    {

        int[] nums = {2,2,1,1,1,2,2};
        Solution solution = new Solution();
        int i = solution.majorityElement(nums);
        System.out.println(i);
    }
    class Solution {
        /**
         * 遍历每个元素
         * count 为现在出现最多的数
         * - 如果count = 0
         *      - 这个元素出现的次数>=之前元素出现的个数
         *          所以基准变为这个元素
         *      count = item == base : 1:-1
         *
         *      如果是这个元素+1,不是这个元素-1
         *
         * @param nums
         * @return
         */
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                    System.out.println("基准元素:" + candidate);
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
}
