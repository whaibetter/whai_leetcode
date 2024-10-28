package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/23 14:25
 * @注释
 */
public class LeetCode61 {
    @Test
    public void test() {
        ListNode head = ListNode.listNodeFromArray(new int[]{1,2,3});
        new Solution().rotateRight(head, 3).printList();
    }
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            int len = 0;
            ListNode tmpHead = head;
            while (tmpHead!= null) {
                len++;
                tmpHead = tmpHead.next;
            }

            k %= len;
            if (k == 0) {
                return head;
            }

            ListNode pre = new ListNode(-1, head);
            ListNode indexPre = pre;
            ListNode indexAfter = pre;
            for (int i = 0; i < k ; i++) {
                indexAfter = indexAfter.next;
            }
            while (indexAfter.next != null) {
                indexAfter = indexAfter.next;
                indexPre = indexPre.next;
            }
            ListNode tmp = indexPre.next;
            indexPre.next = null;
            indexAfter.next = pre.next;
            pre.next = tmp;
            return pre.next;
        }
    }

    @Test
    public void tes1t() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1,2,3});
        new Solution2().rotateRight(listNode, 4).printList();
    }


    class Solution1 {
//        public ListNode rotateRight(ListNode head, int k) {
//            ListNode pre = new ListNode(-1, head);
//            ListNode preIndex = pre;
//            ListNode afterIndex = pre;
//            for (int i = 0; i < k && afterIndex != null; i++) {
//                afterIndex = afterIndex.next;
//            }
//            while (afterIndex.next != null) {
//                afterIndex = afterIndex.next;
//                preIndex = preIndex.next;
//            }
//            afterIndex.next = pre.next;
//            ListNode next = preIndex.next;
//            preIndex.next = null;
//            return next;
//        }
    }

    class Solution2 {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return head;
            }
            ListNode pre = new ListNode(-1, head);
            int len = 0;
            ListNode index = pre;
            while (index.next != null) {
                len++;
                index = index.next;
            }

            int add = len - k % len; // |a|k| 中的a部分
            if (add == 0) {
                return head;
            }

            index.next = pre.next;
            ListNode tmp = pre;
            for (int i = 0; i < add; i++) {
                tmp = tmp.next;
            }
            ListNode cd = tmp.next;
            tmp.next = null;
            return cd;
        }
    }
}
