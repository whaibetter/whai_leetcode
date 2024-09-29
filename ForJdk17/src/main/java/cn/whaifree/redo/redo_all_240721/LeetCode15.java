package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/23 22:46
 * @注释
 */
public class LeetCode15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));

        System.out.println(threeSum(new int[]{1,2,1}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (len < 3) {
            return res;
        }
        Arrays.sort(nums);


        for (int left = 0; left < len - 2; left++) {
            int mid = left + 1;
            int right = len - 1;
            if (left > 0 && nums[left] == nums[left - 1]) {
                continue;
            }
            while (mid < right) {
                int sum = nums[left] + nums[mid] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    while (mid < right && nums[mid] == nums[mid + 1]) {
                        mid++;
                    }
                    while (mid < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 如果有重复的，必然要跳过
                    mid++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    mid++;
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(new Solution().threeSum(new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4}));
    }


    class Solution {
        List<List<Integer>> res = null;
        public List<List<Integer>> threeSum(int[] nums) {
            res = new ArrayList<>();
            threeSum(nums, 0);
            return res;
        }

        public void threeSum(int[] nums, int sum) {
            Arrays.sort(nums);
            int left = 0;

            while (left < nums.length - 2) {
                int right = nums.length - 1;
                if (left > 0 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                int mid = left + 1;
                while (mid < right) {
                    int nowSum = nums[left] + nums[mid] + nums[right];
                    if (nowSum == sum) {
                        res.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                        while (mid < right && nums[mid] == nums[mid + 1]) { // mid是往右探头
                            mid++;
                        }
                        while (mid < right && nums[right] == nums[right - 1]) { // right是往左探头
                            right--;
                        }
                        mid++;
                        right--;
                    } else if (nowSum > sum) {
                        right--;
                    } else {
                        mid++;
                    }
                }

                left++;
            }
        }
    }
}
