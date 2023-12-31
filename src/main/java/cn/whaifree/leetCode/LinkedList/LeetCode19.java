package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LeetCode19 {

    @Test
    public void test() {
        Solution2 solution = new Solution2();
        ListNode listNode = solution.removeNthFromEnd(ListNode.listNodeFromArray(new int[]{1,2,3,4}), 3);
        ListNode.printList(listNode);

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {


        /**
         * 链表中结点的数目为 sz
         * 1 <= sz <= 30
         * 0 <= Node.val <= 100
         * 1 <= n <= sz
         *
         * 时间复杂度：O(Length)
         * 空间复杂度：O(1)
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {

            // 空链表
            if (head == null) {
                return head;
            }
            int length = getLength(head);
            // 删除头
            if (n == length) {
                head = head.next;
                return head;
            }

            int find = length - n;
            ListNode index = head;
            for (int i = 0; i < find - 1; i++) {
                index = index.next;
            }
            index.next = index.next.next;
            return head;
        }

        int getLength(ListNode head) {
            int length = 0;
            ListNode index = head;
            while (index != null) {
                index = index.next;
                length++;
            }
            return length;
        }
    }


    class Solution1 {


        /**
         * 栈
         * 时间复杂度：O(Length)
         * 空间复杂度：O(Length)
         *
         * 修改多使用这个栈会更快
         * LinkedList比Stack快的原因如下1：
         *     基于数组实现：Stack基于数组实现，随机访问（查找）效率更高，增删改效率较低。
         *     基于链表实现：LinkedList基于链表实现，增删改效率更高，随机访问（查找）效率较低。
         * 对于频繁的插入、删除操作，利用LinkedList实现栈自然比Stack快很多。
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(0, head);
            Deque<ListNode> stack = new LinkedList<ListNode>();
            // 双端队列
            ListNode index = dummy;
            while (index != null) {
                stack.push(index);
                index = index.next;
            }
            for (int i = 0; i < n; i++) {
                stack.pop();
            }
            ListNode peek = stack.peek();
            peek.next = peek.next.next;
            return dummy.next;
        }
    }


    class Solution2 {


        /**
         * 双指针 快慢节点追逐：
         *          两个节点的差值为n，快指针走完了，慢指针就是了
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode fast = dummy;
            ListNode slow = dummy;
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }
            while (fast!= null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
            return dummy.next;
        }
    }
}
