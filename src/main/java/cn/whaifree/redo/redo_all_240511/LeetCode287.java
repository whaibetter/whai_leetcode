package cn.whaifree.redo.redo_all_240511;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/2 14:37
 * @注释
 */
public class LeetCode287 {

    @Test
    public void test() {
        int[] nums = {3, 1, 3, 4, 2};
        Solution solution = new Solution();
        int i = solution.findDuplicate(nums);
        System.out.println(i);
    }

    class Solution {
        /**
         * 成环路
         *
         * 1 3 4 2 2
         * 0 1 2 3 4
         *
         * 0--->1--->3--->2--->4--->2
         *
         * 3 1 3 4 2
         * 0 1 2 3 4
         *
         * 0->3-->4-->2
         *
         * 快慢指针
         *，类似链表操作
         * i=0
         * next i=nums[i]
         * nexr.next i=nums[nums[i]]
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
            } while (fast != slow);

            // 找到相交点
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }

            return fast;
        }
    }


}
