package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/5 13:18
 * @注释
 */
public class LeetCode31 {
    @Test
    public void test() {
        int[] nums = {3,2,1};
        new Solution1().nextPermutation(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    class Solution {
        public void nextPermutation(int[] nums) {
            if (nums.length == 1) {
                return;
            }

            // 1.下一个数 比当前数大
            //       将后面的「大数」与前面的「小数」交换，就能得到更大的数
            // 2. 下一个数 增加的幅度尽可能的小
            //       -  尽可能靠右的低位 进行交换
            //       -  将一个 尽可能小的「大数」 与前面的「小数」交换
            //              123465 把 5和4换
            //       -  「大数」后面的所有数 重置为升序
            //              123564 把5后面重新排序

            // 1. 从后往前找到第一个升序排列，此时后面那部分一定是降序
            int i;
            for (i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
            if (i == -1) {
                // 最后一个排序 654321，直接逆转
                reverse(nums, 0, nums.length - 1);
                return;
            }

            // 2. 从i+1开始找到最小的值
            int min;
            for (min = nums.length - 1; min > 0; min--) {
                if (nums[min] > nums[i]) {
                    break;
                }
            }
            // 3. 交换i和minIndex
            swap(nums, i, min);
            // 4. 后面为降序，直接让其逆转变为升序
            reverse(nums, i + 1, nums.length - 1);


        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution1 {
        public void nextPermutation(int[] nums) {
            if (nums.length == 1) {
                return;
            }

            int i = nums.length - 2;
            while (i >= 0) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
                i--;
            }

            if (i == -1) {
                // 最后一个排序 654321，直接逆转
                reverse(nums, 0, nums.length - 1);
                return;
            }

            int min = nums.length - 1;
            while (min > 0) {
                if (nums[min] > nums[i]) {
                    break;
                }
                min--;
            }


            // 3. 交换i和minIndex
            swap(nums, i, min);
            // 4. 后面为降序，直接让其逆转变为升序
            reverse(nums, i + 1, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }



}

class  ass extends as{

    @Override
    public void test() {

    }

    @Override
    void test1() {

    }

    @Override
    protected void test2() {

    }
}


abstract class as{

    abstract public void test() ;

    abstract void test1();

    abstract protected void test2();
}
