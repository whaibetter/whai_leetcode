package cn.whaifree.redo.redo_24_1_7;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode15 {

    @Test
    public void test() {
        int[] nums = {1,0,0};
        List<List<Integer>> lists = new Solution().threeSum(nums);
        System.out.println(lists);
    }


    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            Arrays.sort(nums);

            List<List<Integer>> lists = new ArrayList<>();
            // 计算三个数的和
            for (int i = 0; i < nums.length; i++) {
//
                if (i != 0 && nums[i - 1] == nums[i]) { // 第一次不进入循环，i!=0
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right] + nums[i];
                    if (sum > 0) {
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        lists.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) { // 去重，且避免数组都是一个数字的情况
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) { // 去重，且避免数组都是一个数字的情况
                            right--;
                        }

                    }

                }
            }
            return lists;
        }
    }
}
