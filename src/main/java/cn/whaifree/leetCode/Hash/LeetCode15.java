package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class LeetCode15 {

    @Test
    public void test() {
        new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach(integers -> {
            System.out.println();
            integers.forEach(
                    integer -> System.out.print(integer + " ")
            );
        });
    }

    class Solution {
        /**
         * TODO 2024-1-3 用三指针，没做出来
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> o = new ArrayList<>();
            // 排序后第一个就大于0，必然不可能有结果
            if (nums[0] > 0) {
                return o;
            }
            for (int i = 0; i < nums.length; i++) {

                int left = i + 1;
                int right = nums.length - 1;

                // 如果前后两个i 相同，跳过
                if (i > 0 && nums[i] == nums[i - 1]) {  // 去重a
                    continue;
                }

                while (left < right) {
                    int number = nums[i] + nums[right] + nums[left];
                    if (number > 0) {
                        right--;
                    } else if (number < 0) {
                        left++;
                    } else {
                        o.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重
                        // 如果 right左边的两个一样，去重，跳过去，但依然要保证要比left大
                        while (right > left && nums[right] == nums[right - 1]){
                            right--;
                        }
                        while (right > left && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        right--;
                        left++;
                    }
                }

            }
            return o;
        }
    }
}
