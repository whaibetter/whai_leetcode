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
         * - 如果count = 0 (表示发生了是和不是的变化)
         *      - 这个元素出现的次数>=之前元素出现的个数
         *          所以基准变为这个元素
         *      count = item == base : 1:-1
         *
         *      如果是这个元素+1,不是这个元素-1
         *
         *                 cge5    cge5         cge7
         *  1  2  1  2  1  0   1  0   1  2  1  0   1  2  3  4
         * [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
         * 在遍历到数组中的第一个元素以及每个在 | 之后的元素时，candidate 都会因为 count 的值变为 0 而发生改变。最后一次 candidate 的值从 5 变为 7，也就是这个数组中的众数。
         *
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
