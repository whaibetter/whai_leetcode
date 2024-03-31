package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 15:35
 * @注释
 */
public class LeetCode15 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = new int[]{-2,0,0,2,2};
        List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println(lists);
    }

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length; i++) {

                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > 0) {
                        right--;
                    }else {
                        left++;
                    }
                }
            }
            return res;
        }
    }

}
