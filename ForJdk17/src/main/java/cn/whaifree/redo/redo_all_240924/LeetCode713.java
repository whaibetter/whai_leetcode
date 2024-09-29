package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 12:12
 * @注释
 */
public class LeetCode713 {

    @Test
    public void test() {
        int[] nums = {10,5,2,6};
        int k = 100;
        System.out.println(new Solution().numSubarrayProductLessThanK(nums, k));
    }


    class Solution {

        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int left = 0;
            int right = 0;
            int sum = 1;
            int res = 0;
            while (right < nums.length) {

                sum *= nums[right];
                while (left < right && sum >= k) {
                    // 大了就收缩
                    sum /= nums[left];
                    left++;
                }
                right++;
                if (sum < k) {
                    res += right - left; // 一共有right-left个数字，所以有right-left种可能以right结尾的
                    /**
                     * 如果一个子串的乘积小于k，那么他的每个子集都小于k，而一个长度为n的数组，他的所有连续子串数量是1+2+...n，但是会和前面的重复。
                     * 比如例子中[10, 5, 2, 6]，第一个满足条件的子串是[10]，第二个满足的是[10, 5]，但是第二个数组的子集[10]和前面的已经重复了，
                     * 因此我们只需要计算包含最右边的数字的子串数量，就不会重复了，也就是在计算[10, 5]这个数组的子串是，只加入[5]和[10, 5]，
                     * 而不加入[10]，这部分的子串数量刚好是r - l + 1
                     */

                    /**
                     * 每次 j 增加时，以 j 结尾的满足乘积小于 k 的子数组数量为 j - i + 1。
                     * 因为以 j 结尾的子数组可以从 i 到 j 的任意一个位置作为起点，都满足乘积小于 k。
                     */
                }
            }



            return res;

        }
    }

}
