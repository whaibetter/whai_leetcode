package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/17 13:58
 * @注释
 */
public class LeetCode238 {
    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        int[] ints = productExceptSelf(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    class Solution {
        /**
         * [-1,1,0,-3,3]
         * -1 -1 0 0 0
         * 0 0  0 -9 3
         *
         * @param nums
         * @return
         */
        public int[] productExceptSelf(int[] nums) {
            int[] preMul = new int[nums.length];
            preMul[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preMul[i] = preMul[i - 1] * nums[i];
            }

            int[] afterMul = new int[nums.length];
            afterMul[nums.length - 1] = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                afterMul[i] = afterMul[i + 1] * nums[i];
            }

            for (int i = 0; i < nums.length; i++) {
                int pre = (i > 0) ? preMul[i - 1] : 1;
                int after = i < nums.length - 1 ? afterMul[i + 1] : 1;
                nums[i] = pre * after;
            }

            return nums;
        }
    }

    //维护两个变量，beforeSum表示前缀和，afterSum表示后缀和
    // 两个指针不断缩小会相交
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int beforeSum = 1;
        int afterSum = 1;

        int left = 0;
        int right = nums.length - 1;
        while (left < nums.length) {
            ans[left] *= beforeSum;
            ans[right] *= afterSum;
            beforeSum *= nums[left];
            afterSum *= nums[right];
            left++;
            right--;
        }

        return ans;
    }
}
