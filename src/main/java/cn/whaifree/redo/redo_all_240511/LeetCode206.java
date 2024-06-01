package cn.whaifree.redo.redo_all_240511;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/24 19:28
 * @注释
 */
public class LeetCode206 {


    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5});
        listNode.printList();
        ListNode listNode1 = new Solution().reverseList(listNode);
        listNode1.printList();
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode reverse = reverse(null, head);
            return reverse;
        }

        public ListNode reverse(ListNode pre, ListNode after) {
            if (after == null) {
                return pre;
            }
            ListNode next = after.next;
            after.next = pre;
            ListNode reverse = reverse(after, next);


            return reverse;

        }
    }
}
