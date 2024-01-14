package cn.whaifree.redo.redo24_1_7;


import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

public class LeetCode203_false {

    @Test
    public void test(){
        new Solution().removeElements(ListNode.listNodeFromArray(new int[]{1, 2}), 2).printList();
    }


    // 使用递归
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

        public ListNode removeElements(ListNode head, int val) {
            return delete(head, val);
        }

        // 0 1 2
        public ListNode delete(ListNode head, int val) {
            if (head == null) {
                return head;
            }

            head.next = delete(head.next, val);
            if (head.val == val) {
                return head.next;
            } else {
                return head;
            }
        }





    }

}
