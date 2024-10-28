package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * 977. 有序数组的平方
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *
 *
 * 进阶：
 *
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * 更多挑战
 * 88. 合并两个有序数组
 * 360. 有序转化数组
 */
public class LeetCode977 {

    /**
     * 找到第一个左边小于0，右边大于等于0的点
     * 左右两个指针
     * 平方后必然是，【递减 0 递增】
     * [-3,-1,0,2,5]
     * [9,1,0,4,25]
     *
     * 让左边的循环插入到右边合适的位置
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {

        int middle = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                middle = i;
                break;
            }
        }
        // 此时就有两个数组了
        // [0,middle-1] 为递减的
        // [middle, length-1] 为递增的
        int[] ints = new int[nums.length];
        int leftIndex = middle -1;
        int rightIndex = middle;
        int intIndex = 0;
        while (leftIndex >= 0 && rightIndex < nums.length) {
            if (nums[leftIndex] * nums[leftIndex] < nums[rightIndex] * nums[rightIndex]) {
                ints[intIndex++] = nums[leftIndex] * nums[leftIndex];
                leftIndex--;
            } else {
                ints[intIndex++] = nums[rightIndex] * nums[rightIndex];
                rightIndex++;
            }
        }

        while (leftIndex >= 0) {
            ints[intIndex++] = nums[leftIndex] * nums[leftIndex];
            leftIndex--;
        }
        while (rightIndex <= nums.length-1) {
            ints[intIndex++] = nums[rightIndex] * nums[rightIndex];
            rightIndex++;
        }

        return ints;
    }


    /**
     * 不考虑0的双指针
     * @param nums
     * @return
     */
    public int[] sortedSquares1(int[] nums) {
        int[] ints = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                ints[index++] = nums[right] * nums[right];
                right--;
            } else {
                ints[index++] = nums[left] * nums[left];
                left++;
            }
        }

        // 逆序
        int value = 0;
        for (int i = 0; i < ints.length / 2; i++) {
            value = ints[i];
            ints[i] = ints[ints.length - i - 1];
            ints[ints.length - 1 - i] = value;
        }

        return ints;
    }

    public int[] sortedSquares2(int[] nums) {
        int[] ints = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;
        // 存入新数组的索引，从大往小存
        int index = nums.length - 1;
        while (left <= right) {
            // 负数 + 正数 > 0  则 |负数| < |正数|
            if (nums[left] + nums[right] > 0) {
                ints[index--] = nums[right] * nums[right];
                right--;
            } else {
                ints[index--] = nums[left] * nums[left];
                left++;
            }
        }


        return ints;
    }


    @Test
    public void test() {
        int[] ints = sortedSquares2(new int[]{-3,-1,4,10});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
