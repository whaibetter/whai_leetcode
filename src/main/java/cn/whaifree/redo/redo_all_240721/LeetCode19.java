package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/9 21:20
 * @注释
 */
public class LeetCode19 {
    @Test
    public void test() {

//        new Solution().removeNthFromEnd(ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5}), 2).printList();
        new So().ListNoderemoveNthEromEnd(ListNode.listNodeFromArray(new int[]{1}), 1).printList();
    }


    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode pre = new ListNode(-1, head);
            ListNode fast = pre;
            ListNode slow = pre;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
            return pre.next;
        }
    }

    class So {
        public ListNode ListNoderemoveNthEromEnd(ListNode head, int n) {
            ListNode first = new ListNode(0, head);
            ListNode second = head;
            for (int i = 0; i < n; i++) {
                second = second.next;
            }
            while (second != null) {
                first = first.next;
                second = second.next;
            }
            first.next = first.next.next;
            return head;

        }
    }
}
