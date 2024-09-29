package cn.whaifree.redo.redo.redo_24_1_13;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/14 12:53
 * @注释
 */
public class LeetCode287 {

    @Test
    public void test() {
        System.out.println(new Solution().findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }


    class Solution {

        /**
         * 寻找循环链表的入口
         * 1 3 4 2 2
         * 0 1 2 3 4
         *
         * 0--->1--->3--->2--->4--->2
         *
         *
         *
         * @param nums
         * @return
         */
        public int findDuplicate(int[] nums) {

            // 快慢指针
            int fast = 0;
            int slow = 0;


            // 找到相遇点
            do {
                fast = nums[nums[fast]];
                slow = nums[slow];
            }
            while (fast != slow);

            fast =0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            // 快指针指向头部，两个指针同志向前，直到相遇

            return fast;
        }

    }


}
