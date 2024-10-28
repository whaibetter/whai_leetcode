package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

public class LeetCode_0207_IntersectionNode {

    @Test
    public void test() {
        Solution1 solution = new Solution1();
        ListNode headA = ListNode.listNodeFromArray(new int[]{1, 2, 3});
        ListNode headB = ListNode.listNodeFromArray(new int[]{5});
        ListNode listNode = new ListNode(10);

        ListNode nodeA = headA;
        while (nodeA.next != null) {
            nodeA = nodeA.next;
        }
        nodeA.next = listNode;

        ListNode nodeB = headB;
        while (nodeB.next != null) {
            nodeB = nodeB.next;
        }
        nodeB.next = listNode;

        ListNode intersectionNode = solution.getIntersectionNode(headA, headB);
        if (intersectionNode != null) {
            System.out.println(intersectionNode.val);
        } else {
            System.out.println("null");
        }

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    class Solution {

        /**
         * 先找到长的，让长的指针移动到同步
         * @param headA
         * @param headB
         * @return
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lengthA = getLength(headA);
            int lengthB = getLength(headB);

            if (lengthB < lengthA) {
                int sub = lengthA - lengthB;
                ListNode indexA = headA;
                ListNode indexB = headB;
                for (int i = 0; i < sub; i++) {
                    indexA = indexA.next;
                }

                while (indexA != null) {
                    if (indexA == indexB) {
                        return indexA;
                    }
                    indexA = indexA.next;
                    indexB = indexB.next;
                }

            } else {
                int sub = lengthB - lengthA;
                ListNode indexA = headA;
                ListNode indexB = headB;
                for (int i = 0; i < sub; i++) {
                    indexB = indexB.next;
                }
                while (indexA != null) {
                    if (indexA == indexB) {
                        return indexA;
                    }
                    indexA = indexA.next;
                    indexB = indexB.next;
                }
            }
            return null;
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
         *  两个链表交替双指针
         *
         *  -
         *
         * @param headA
         * @param headB
         * @return
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

            if (headA == null || headB == null) {
                return null;
            }

            ListNode indexA = headA;
            ListNode indexB = headB;
            // 链表没有公共节点的时候, indexA和b都为null，会跳出循环
            while (indexB != indexA) {

                if (indexA == null) {
                    indexA = headB;
                }else {
                    indexA = indexA.next;
                }

                if (indexB == null) {
                    indexB = headA;
                }else {
                    indexB = indexB.next;
                }
            }
            return indexA;
        }
    }

}
