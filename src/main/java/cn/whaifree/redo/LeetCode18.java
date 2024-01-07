package cn.whaifree.redo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode18 {

    @Test
    public void test() {
//        System.out.println(new Solution().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        int[] nums = new int[]{-3,-2,-1,0,0,1,2,3};
        // [-3,0,1,2]
        System.out.println(new Solution().fourSum(nums, 0));
    }

    // 四个数之和
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();

            if (nums[0] > target && nums[0] > 0) {
                return lists;
            }

            for (int i = 0; i < nums.length; i++) {

                // 第一次给过
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }


                for (int j = i + 1; j < nums.length; j++) {

                    // 第一次给过
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }

                    int rignt = nums.length - 1;
                    int left = j + 1;
                    while (left < rignt) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[rignt];
                        if (sum > target) {
                            rignt--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            lists.add(Arrays.asList(nums[i], nums[j], nums[left], nums[rignt]));

                            while (left < rignt && nums[left + 1] == nums[left]) left++;
                            // 注意这里 right-1
                            while (left < rignt && nums[rignt - 1] == nums[rignt]) rignt--;

                            left++;
                            rignt--;

                        }

                    }


                }

            }
            return lists;
        }
    }
}
