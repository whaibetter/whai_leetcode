package cn.whaifree.leetCode.Array;


import org.junit.Test;

public class LeetCode287 {

    @Test
    public void test() {
        System.out.println(new Solution().findDuplicate(new int[]{1, 3, 3, 2}));
    }

    class Solution {
        /**
         * 将数组转换为链表思想
         *   [ 1, 3, 4, 2, 2]
         *     0  1  2  3  4
         *
         *   0->1->3->[2->4->2->4->2.....]
         *
         *         ListNode index = index.next
         *   等价于 int index = nums[index]
         *
         *
         *  -  nums.length == n + 1
         *  -  1 <= nums[i] <= n
         *  表明 如果只有3个元素，取值区间只能为[1,2,3]
         *
         * @param nums
         * @return
         */
        public int findDuplicate(int[] nums) {

            // 因为 1 <= n <= 105 所以0只在开始出现过。
            // 两个指针
            int fast = 0;
            int slow = 0;
            slow = nums[slow];
            fast = nums[nums[fast]];
            // 找到相遇的地方
            while (fast != slow) {
                fast = nums[nums[fast]];
                slow = nums[slow];
            }
            // 此时两个指针指向同个地方，表示在环的里面
            int tmp = 0;
            while (nums[tmp] != nums[slow]) {
                tmp = nums[tmp];
                slow = nums[slow];
            }

            return nums[tmp];
        }
    }
}
