package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class LeetCode35 {

    /**
     * left左边的值一直保持小于target，right右边的值一直保持大于等于target，
     * 而且left最终一定等于right+1，
     * 这么一来，循环结束后，
     * 在left和right之间画一条竖线，恰好可以把数组分为两部分：
     *  left左边的部分和right右边的部分，而且left左边的部分全部小于target，并以right结尾；
     *  right右边的部分全部大于等于target，并以left为首。
     *
     *  所以最终答案一定在left的位置。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        // 定义左右指针，分别指向数组的起始位置和结束位置
        int left = 0;
        int right = nums.length - 1;
        // 当左指针小于等于右指针时，进行循环
        while (left <= right) {
            // 计算中间位置的索引 右边距减去左边距，右移一位，然后加上左边距，得到中间值
            // right-left 就是从0开始到n， 右移动一位就是除以2，获得到这个区间的一半，加上起始的left 就是(right + left)/2
            int middle = ((right - left) >> 1) + left;
            // 如果中间位置的元素等于目标元素，返回中间位置的索引
            if (nums[middle] == target) {
                return middle;
            // 如果中间位置的元素大于目标元素，将右指针移动到中间位置的左侧一个位置
            } else if (nums[middle] > target) {
                right = middle - 1;
            // 如果中间位置的元素小于目标元素，将左指针移动到中间位置的右侧一个位置
            } else {
                left = middle + 1;
            }
        }
        // 当找不到目标元素时，返回左指针的值
        return left;
    }


    public int searchInsert1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (right + left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle ;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }


    @Test
    public void Test() {
        int[] ints = {1, 3, 5, 8,10};
        int[] ints1 = {};

        int middle = ((8 - 4) >> 1) + 4;

        // 1000 - 0100 = 0100 >> 1 = 0010 ==> 2 + 4
        System.out.println(searchInsert1(ints, 2));
    }
}
