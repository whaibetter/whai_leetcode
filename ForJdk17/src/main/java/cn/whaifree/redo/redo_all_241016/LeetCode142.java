package cn.whaifree.redo.redo_all_241016;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 22:53
 * @注释
 */
public class LeetCode142
{
   @Test
    public void test() {
        ListNode head = new ListNode(3);
       ListNode next1 = new ListNode(2);
       head.next = next1;
        head.next.next = new ListNode(0);
       ListNode next = new ListNode(-4);
       head.next.next.next = next;

       next.next = next1;
        ListNode node = new Solution().detectCycle(head);
        System.out.println(node.val);
    }
    public class Solution {
        /**
         * slow 走了N
         * fast 走了2N 相交
         * 那么一个环=x+N，
         * 此时slow就在x，而
         *
         *     | x|
         * 0------N.---    N=入口+x      环-x=N
         *     |      |    2N-入口 = 环
         *     --------
         *
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            if (fast == null || fast.next == null) {
                return null;
            }

            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
}
