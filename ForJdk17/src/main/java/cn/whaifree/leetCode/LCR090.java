package cn.whaifree.LCR;

import org.junit.Test;

import java.util.HashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 20:16
 * @注释
 */
public class LCR090{

    @Test
    public void test()
    {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null, "");
        System.out.println(objectObjectHashMap.get(null));
        System.out.println(objectObjectHashMap.get("a"));

        int[] nums = {1,2,1,1};
        System.out.println(new Solution().rob(nums));
    }


    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }

            return Math.max(
                    robRange(nums, 0, nums.length - 2),
                    robRange(nums, 1, nums.length - 1)
            );
        }

        public int robRange(int[] nums, int start, int end) {
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }


    }
}
