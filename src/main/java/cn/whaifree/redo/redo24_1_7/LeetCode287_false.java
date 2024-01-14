package cn.whaifree.redo.redo24_1_7;

import cn.hutool.core.lang.hash.Hash;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LeetCode287_false {

    @Test
    public void test() {
        System.out.println(new Solution().findDuplicate(new int[]{1, 3, 4, 3, 2}));
    }

    class Solution {
        /**
         * 要求 O（N）的时间复杂度
         *     O（1）的控空间
         *
         *     将数组转换为链表的思想，即求有没有形成环
         *     1 3 4 2 2
         *     0 1 2 3 4
         *  0-->1-->2-->4--->2
         *
         * @param nums
         * @return
         */
        public int findDuplicate(int[] nums) {
            // 1. 找到相遇的点
            // 2. 将快指针指向首部
            // 3. 两个指针重合处就是

            int fast = 0;
            int slow = 0;

            do {
                fast = nums[nums[fast]];
                slow = nums[slow];
            } while (fast != slow);

            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }

            return fast;
        }
    }
}
