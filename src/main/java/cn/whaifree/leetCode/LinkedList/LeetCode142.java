package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;

public class LeetCode142 {

    @Test
    public void test() {



        Solution solution = new Solution();
        ListNode listNode1= new ListNode(1);
        ListNode listNode2= new ListNode(2,listNode1);
        ListNode listNode3= new ListNode(3,listNode2);
        ListNode listNode4= new ListNode(4,listNode3);
        listNode1.next = listNode3;

//        ListNode.printList(listNode4);

        ListNode listNode = solution.detectCycle(listNode4);
        if (listNode == null) {
            System.out.println("null");
        } else {
            System.out.println(listNode.val);
        }
    }

    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        /**
         * 快慢指针
         * 时间复杂度：O(N)，其中 N 为链表中节点的数目。我们恰好需要访问链表中的每一个节点。
         * 空间复杂度：O(1)
         *
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {

            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                // 相遇了，有环。
                if (slow == fast) {
                    break;
                }
            }

            if (Objects.isNull(fast) || Objects.isNull(fast.next)) {
                return null;
            }

            // 此时相遇 fast,slow
            // x = (n-1)(y-z)+z
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }


    /**
     *  时间复杂度：O(N)，其中 N 为链表中节点的数目。我们恰好需要访问链表中的每一个节点。
     *  空间复杂度：O(N)，其中 N 为链表中节点的数目。我们需要将链表中的每个节点都保存在哈希表当中。
     */
    public class Solution1 {
        /**
         * 快慢指针
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {
            HashSet<ListNode> listNodes = new HashSet<>();
            ListNode index = head;
            while (index != null && !listNodes.contains(index)) {
                listNodes.add(index);
                index = index.next;
            }
            return index;
        }
    }
}
