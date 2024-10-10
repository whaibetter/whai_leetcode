package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/10 11:36
 * @注释
 */
public class LeetCode238 {

    @Test
    public void test()
    {
        int[] nums = new int[]{-1,1,0,-3,3};
        Solution solution = new Solution();
        int[] res = solution.productExceptSelf(nums);
        for (int i : res) {
            System.out.println(i);
        }
    }

    class Solution {
        public int[] productExceptSelf(int[] nums) {

            int countOfZero = 0;
            int product = 1;
            int zeroIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num == 0) {
                    countOfZero++;
                    zeroIndex = i;
                }else {
                    product *= num;
                }
            }

            if (countOfZero >= 2) {
                return new int[nums.length];
            }

            if (countOfZero == 1) {
                int[] res = new int[nums.length];
                res[zeroIndex] = product;
                return res;
            }

            int[] res = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                res[i] = product / nums[i];
            }
            return res;
        }
    }
}
