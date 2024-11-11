package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/6 13:20
 * @注释
 */
public class LeetCode15 {

    @Test
    public void test() {
        int[] nums = {1,2,-2,-1};
        System.out.println(new Solution().threeSum(nums));
    }

    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int left = 0; left < nums.length; left++) {

                if (left > 0 && nums[left] == nums[left - 1]) {
                    continue;
                }

                int mid = left + 1;
                int right = nums.length - 1;
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
                        mid++;
                        right--;
                    } else if (sum < 0) {
                        mid++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }
    }

}
