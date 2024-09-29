package cn.whaifree.redo.redo.redo_24_4_27;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/29 12:07
 * @注释
 */
public class LeetCode75 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {2,0,2,1,1,0};
        solution.sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    class Solution {
        public void sortColors(int[] nums) {
            int n0 = 0, n1 = 0; // 0的数量  0和1的数量
            for(int i = 0; i < nums.length; i++){
                int num = nums[i];
                nums[i] = 2;
                if(num < 2){
                    nums[n1++] = 1;
                }
                if(num < 1){
                    nums[n0++] = 0;
                }
            }
        }
    }
}
