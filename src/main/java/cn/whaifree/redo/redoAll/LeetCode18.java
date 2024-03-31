package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 12:46
 * @注释
 */
public class LeetCode18 {

    @Test
    public void test()
    {
        int[] nums = {-1,0,-5,-2,-2,-4,0,1,-2};
        int target = -9;
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.fourSum(nums, target);
        System.out.println(lists);
    }

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            if (nums[0] > target && nums[0] > 0) {
                return res;
            }

            for (int i = 0; i < nums.length; i++) {
                // 重复去除
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                for (int j = i + 1; j < nums.length; j++) {
                    // 第一次给过
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    int left = j + 1;
                    int right = nums.length - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            while (right > left && nums[left] == nums[left+ 1]) {
                                left++;
                            }
                            while (right > left && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            left++;
                            right--;
                        } else if (sum > target) {
                            right--;
                        }else {
                            left++;
                        }

                    }
                }
            }
            return res;
        }
    }

}
