package cn.whaifree.redo.redo.redo_24_1_13;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/14 14:20
 * @注释
 */
public class LeetCode203 {

    @Test
    public void test() {
        ListNode listNode = new Solution().removeElements(ListNode.listNodeFromArray(new int[]{7,7,7,7}), 7);
        ListNode.printList(listNode);

    }


    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return null;
            }

            ListNode pre = new ListNode(0, head);
            ListNode remove = remove(pre, val);
            return remove.next;

        }

        public ListNode remove(ListNode head, int val) {
            if (head == null) {
                return null;
            }

//            ListNode listNode = removeElements(head.next, val);

            /**
             * 只考虑最后两个节点
             * A B null
             *
             */
            head.next = remove(head.next, val);
            if (head.val == val) {
                return head.next;
            } else {
                return head;
            }
        }
    }

}
