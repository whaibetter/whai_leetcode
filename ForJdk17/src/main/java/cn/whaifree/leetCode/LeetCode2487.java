package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

public class LeetCode2487 {
    @Test
    public void test() {
        new Solution1().removeNodes(ListNode.listNodeFromArray(new int[]{5, 2, 13, 3, 8})).printList();
    }


    /**
     * 找到最大的节点，找到下一个最大的节点，此区间全部删掉。
     *   每次删除后MaxNode为null
     *
     *   即最后变成了递减的链表
     *
     */
    class Solution {
        /**
         * 递归
         * @param head
         * @return
         */
//        public ListNode removeNodes(ListNode head) {
//            ListNode dummy = new ListNode(0, head);
//            ListNode pre = dummy;
//            ListNode index = head;
//            ListNode maxNode = dummy;
//            while (index != null) {
//                if (index.val > maxNode.val) {
//                    maxNode = index;
//                    // 删除pre到maxNode的前一个节点
//                    pre.next = maxNode;
//                    maxNode.next = removeNodes(maxNode.next);
//                }
//                index = index.next;
//            }
//
//            return dummy.next;
//        }
        public ListNode removeNodes(ListNode head) {
            return remove(new ListNode(0, head));
        }

        public ListNode remove(ListNode head) {
            if (head == null) {
                return null;
            }

            head.next = remove(head.next);
            if (head.next != null && head.val < head.next.val) {
                return head.next;
            } else {
                return head;
            }
        }

    }

    class Solution1 {


        public ListNode removeNodes(ListNode head) {

            ListNode newHead = reverse(head);
            // 变成从右往左边移除比newHead小的
            ListNode index = newHead;
            ListNode tmpMaxNode = newHead;
            while (index.next != null) {
                if (index.next.val < tmpMaxNode.val) {
                    index.next = index.next.next;
                } else {
                    index = index.next;
                    tmpMaxNode = index;
                }
            }
            // 最后那个数是永远不会删除的
            return reverse(newHead);
        }

        public ListNode reverse(ListNode head) {
            // 反转链表
            ListNode index = head;
            ListNode pre = null;

            while (index != null) {
                ListNode tmp = index.next;
                index.next = pre;
                pre = index;
                index = tmp;
            }
            // {5, 2, 13, 3, 8}
            return pre;
        }
    }
}
