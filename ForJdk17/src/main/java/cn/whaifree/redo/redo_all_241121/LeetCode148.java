package cn.whaifree.redo.redo_all_241121;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 13:52
 * @注释
 */
public class LeetCode148 {

    @Test
    public void test() {

        ListNode listNode1 = ListNode.listNodeFromArray(new int[]{1,2,3,4});
        ListNode listNode = new Solution().sortList(listNode1);
        listNode.printList();
    }

    class Solution {
        /**
         *
         * 归并
         *
         *
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {

            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head.next; // 注意不要一起开始
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode BHead = slow.next;
            slow.next = null;


            ListNode A = sortList(head);
            ListNode B = sortList(BHead);
            return merge(A, B);
        }

        public ListNode merge(ListNode A, ListNode B) {
            ListNode newHead = new ListNode(-1);
            ListNode index = newHead;
            while (A != null && B != null) {
                if (A.val < B.val) {
                    index.next = A;
                    A = A.next;
                } else {
                    index.next = B;
                    B = B.next;
                }
                index = index.next;
            }
            while (A != null) {
                index.next = A;
                A = A.next;
                index = index.next;
            }
            while (B != null) {
                index.next = B;
                B = B.next;
                index = index.next;
            }
            return newHead.next;
        }

    }

}
