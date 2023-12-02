package cn.whaifree.leetCode.easy;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 二分查找
 *
 *
 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 示例 1:

 输入: nums = [-1,0,3,5,9,12], target = 9
 输出: 4
 解释: 9 出现在 nums 中并且下标为 4
 示例 2:

 输入: nums = [-1,0,3,5,9,12], target = 2
 输出: -1
 解释: 2 不存在 nums 中因此返回 -1


 提示：

 你可以假设 nums 中的所有元素是不重复的。
 n 将在 [1, 10000]之间。
 nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class LeetCode704 {


    /**
     * 二分查找 [left,right]
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        // 初始化指针
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (left < right) {
            // 计算中间索引
            index = (right + left) / 2;
            if (nums[index] >= target) {
                right = index;
            } else{
                left = index + 1;
            }
        }
        if (nums[(right + left) / 2] == target) {
            return (right + left) / 2;
        }


        return -1;

    }

    public int search1(int[] nums, int target) {
        int left = 0; // 左指针，初始指向数组最左边的位置
        int right = nums.length - 1; // 右指针，初始指向数组最右边的位置
        while (left <= right) { // 当左指针小于等于右指针时，循环执行
            int mid = left + (right - left) / 2; // 中间指针，计算左指针和右指针的中间位置
            if (nums[mid] == target) { // 如果中间位置的数值等于目标数值
                return mid; // 返回中间位置的索引
            } else if (nums[mid] < target) { // 如果中间位置的数值小于目标数值
                left = mid + 1; // 将左指针移动到中间位置的右边
            } else { // 如果中间位置的数值大于目标数值
                right = mid - 1; // 将右指针移动到中间位置的左边
            }
        }
        return -1; // 没有找到目标数值，返回-1
    }


    /**
     * 二分查找 [left,right)
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[left] == target) {
                return left;
            } else if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle+1;
            }
        }

        return -1;

    }





    @Test
    public void testSearch1() {
        LeetCode704 solution = new LeetCode704();

        int[] nums = {1};
        int target = 5;
        Assert.assertEquals(-1, solution.search2(nums, target));

        nums = new int[]{10, 20, 30, 40};
        target = 30;
        Assert.assertEquals(2, solution.search1(nums, target));

        nums = new int[]{1, 2, 3};
        target = 4;
        Assert.assertEquals(-1, solution.search1(nums, target));


        nums = new int[]{1};
        target = 1;
        Assert.assertEquals(0, solution.search1(nums, target));

    }
}
