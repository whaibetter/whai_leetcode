package cn.whaifree.redo.redo_all_240924;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/30 16:37
 * @注释
 */
public class FindAbsMinItem {

    public static void main(String[] args) {
        int[] nums = {-4, -2,  1, 10};
        FindAbsMinItem findAbsMinItem = new FindAbsMinItem();
        System.out.println(findAbsMinItem.findAbsMin(nums, 0));
    }

    public int findAbsMin(int[] nums,int target) {
        int i = find(nums, target);
        if (i < 0) {
            // 没找到
            i = -i;
            if (Math.abs(nums[i - 1]) < Math.abs(nums[i])) {
                return Math.abs(nums[i - 1]);
            }else {
                return Math.abs(nums[i]);
            }
        }
        if (i == 0) {
            // 0在最前面，那么绝对值是递增的
            return nums[i];
        }
        return nums[i];
    }
    public int find(int[] nums,int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -left;
    }
}
