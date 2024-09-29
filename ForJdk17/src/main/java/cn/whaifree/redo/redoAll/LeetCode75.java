package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 20:09
 * @注释
 */
public class LeetCode75 {

    @Test
    public void test()
    {
        int[] nums = {2,1,0};
        Solution1 solution = new Solution1();
        solution.sortColors(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    class Solution {
        public void sortColors(int[] nums) {

            int left = 0;
            int right = nums.length - 1;
            for (int i = 0; i < nums.length; i++) {
                while (right >= i && nums[i] == 2) { // 万一换过来的还是2
                    swap(nums, right, i);
                    right--;
                }
                if (nums[i] == 0) { // 每个0只要往前插入就好了
                    swap(nums, left++, i);
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution1 {
        /**
         * 双指针：两个指针指向0和1应该swap的地方
         *
         * @param nums
         */
        public void sortColors(int[] nums) {

            int indexZero = 0;
            int indexOne = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, indexZero, i);
                    // 此时有可能index0指向的是1，这样交换会把1交换到后面去
                    // 即交换后的i指向的可能为1
                    // 需要把这个1给他替换到index1对应的位置
//                    if (indexZero < indexOne) {
//                        swap(nums, i, indexOne);
//                    }
                    indexOne++;
                    indexZero++;
                } else if (nums[i] == 1) {
                    swap(nums, indexOne++, i);
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
