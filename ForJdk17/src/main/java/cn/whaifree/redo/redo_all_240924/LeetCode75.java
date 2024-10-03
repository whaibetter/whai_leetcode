package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/1 13:45
 * @注释
 */
public class LeetCode75 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {2,0,2,1,1,1};
        solution.sortColors(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    class Solution {
        /**
         * 2 0 2 1 1 0
         * 0 0 2 1 1 2
         * 0 0 1 1 2 2
         *
         * @param nums
         */
        public void sortColors(int[] nums) {
            int index0 = 0;
            int index2 = nums.length - 1;
            for (int i = 0; i < nums.length; i++) {
                while (index2 >= i && nums[i] == 2) {
                    swap(nums, index2--, i);
                }
                if (nums[i] == 0) {
                    swap(nums, index0++, i);
                }
            }
        }
        public void swap(int[] nums,int i,int j)
        {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        for (int i = 100; i < 1000; i++) {
            set.add(i);
        }

        long l1 = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
//        if (it)

    }
}
