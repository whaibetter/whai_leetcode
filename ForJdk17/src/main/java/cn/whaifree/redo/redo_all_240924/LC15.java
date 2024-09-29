package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 10:25
 * @注释
 */
public class LC15{

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println(lists);
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> threeSum(int[] nums) {
            return threeSum(nums, 0);
        }


        public List<List<Integer>> threeSum(int[] nums, int target) {
            Arrays.sort(nums);
            for (int left = 0; left < nums.length - 2; left++) {

                if (left > 0 && nums[left] == nums[left - 1]) {
                    continue;
                }

                int right = nums.length - 1;
                int mid = left + 1;

                while (mid < right) {
                    int tmpSum = nums[left] + nums[mid] + nums[right];
                    if (tmpSum == target) {
                        res.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                        while (mid < right && nums[mid] == nums[mid + 1]) {
                            mid++;
                        }
                        while (mid < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        mid++;
                        right--;
                    }else if (tmpSum > target) {
                        right--;
                    } else {
                        mid++;
                    }


                }
            }
            return res;
        }
    }
}
