package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/27 11:42
 * @注释
 */
public class LeetCode1005 {

    @Test
    public void test() {
        System.out.println(new Solution().largestSumAfterKNegations(new int[]{-2,5,0,2,-2}, 3));
        System.out.println(new Solution().largestSumAfterKNegations(new int[]{4,2,3}, 1))
        ;

        System.out.println(new Solution1().largestSumAfterKNegations(new int[]{-2,5,0,2,-2}, 3));
        System.out.println(new Solution1().largestSumAfterKNegations(new int[]{4,2,3}, 1));
    }


    class Solution {

        /**
         * 注意几个例子
         * 1. nums = [3,-1,0,2], k = 3   选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2]
         *
         * -2,5,1,2,-2
         * 按绝对值逆转   5 -2 2 -2 1
         * 每次遇到负数，变为相反数
         * // 剩下的k如果是奇数，就把最后一个逆转
         *
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 按绝对值从大到小排序，对前k个负数进行相反数
            nums = IntStream.of(nums)
                    .boxed()
                    .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                    .mapToInt(Integer::intValue).toArray();


            for (int i = 0; i < nums.length; i++) {
                if (k > 0 && nums[i] < 0) {
                    nums[i] = -nums[i];
                    k--;
                }
            }

            // 此时还有k个没减去,k为偶数则不管，k为奇数就把最小那个变为正数
            if (k % 2 == 1) {
                nums[nums.length-1] = -nums[nums.length-1];
            }


            return Arrays.stream(nums).sum();
        }
    }

    class Solution1 {

        /**
         * 注意几个例子
         * 1. nums = [3,-1,0,2], k = 3   选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2]
         *
         * -2,5,1,2,-2
         * 排序
         * -2 -2 1 2 5
         * 逆转
         * 2 2 1 2 5
         * 将最下的数逆转,sum-min-min
         * 2 2 -1 2 5
         *
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 按绝对值从大到小排序，对前k个负数进行相反数
            Arrays.sort(nums);


            int sum = 0;
            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (k > 0 && nums[i] < 0) {
                    nums[i] = -nums[i];
                    k--;
                }
                sum += nums[i];
                minValue = Math.min(minValue, nums[i]);
            }

            // 此时还有k个没减去,k为偶数则不管，k为奇数就把最小那个变为正数
            if (k % 2 == 1) {
                // 减去在遍历过程中加的minValue部分和通过变换负数的部分
                return sum - minValue - minValue;
            }else {
                return sum;
            }

        }
    }

}
