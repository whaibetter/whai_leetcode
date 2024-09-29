package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 11:40
 * @注释
 */
public class LeetCode287 {

    @Test
    public void test()
    {

        int[] nums = {3,1,3,4,2};
        int i = new Solution1().findDuplicate(nums);
        System.out.println(i);
    }

    class Solution {
        public int findDuplicate(int[] nums) {
            HashSet<Integer> map = new HashSet<>();
            for (int num : nums) {
                if (map.contains(num)) {
                    return num;
                }else {
                    map.add(num);
                }
            }
            return -1;
        }
    }

    class Solution1 {
        /**
         * 循环链表的入口
         * 0 1 2 3 4
         * 3 1 3 4 2
         *
         * 0 3 4 2 3
         *
         * 0-->1--->3---->2--->4
         *                |----|
         *
         * @param nums
         * @return
         */
        public int findDuplicate(int[] nums) {
            int fast = 0;
            int slow = 0;
            do {
                fast = nums[nums[fast]];
                slow = nums[slow];
            }
            while (fast != slow);
            // 此时找到交会点

            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return fast;
        }
    }

}
