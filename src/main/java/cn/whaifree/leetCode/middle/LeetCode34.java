package cn.whaifree.leetCode.middle;

import org.junit.Test;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 中等
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class LeetCode34 {
    /**
     * 非递减顺序排列的整数数组
     * [0,length]
     * [5,8,8,8,8,10]
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        // 三头
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                // 在[left，middle) , (middle,right]区间里找到第一个和最后一个target

//                break;
                // 找到了一个
                if (nums[left] == target && nums[right] == target) {
                    ans[0] = left;
                    ans[1] = right;
                    return ans;
                } else if (nums[left] == target) {
                    right--;
                } else if (nums[right] == target) {
                    left++;
                } else {
                    right--;
                    left++;
                }
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }
    public int[] searchRange2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }



    @Test
    public void test() {
        for (int i : searchRange2(new int[]{5,8,8,8,8,10}, 8)) {
            System.out.println(i);
        }
    }

}
