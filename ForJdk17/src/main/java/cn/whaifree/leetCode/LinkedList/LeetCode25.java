package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/23 13:43
 * @注释
 */
public class LeetCode25 {

    @Test
    public void test() {
        ListNode head = ListNode.listNodeFromArray(new int[]{1, 2, 3, 6, 7, 8, 9, 20});
//        new Solution().reverse(null, head, 3).printList();
        new Solution().reverseKGroup(head, 2).printList();
    }

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode pre = new ListNode(-1, head);
            return reverseRange(pre.next, k);
        }


        /**
         * pre往后k个元素翻转
         * @param pre
         * @param k
         * @return
         */
        public ListNode reverseRange(ListNode pre, int k) {
            ListNode index = pre;
            ListNode nextNode = pre;
            int i = 0;
            while (i < k && nextNode != null) {
                nextNode = nextNode.next;
                i++;
            }
            if (i < k) {
                return pre;
            }
            ListNode reverseAfterHead = reverse(null, index, k);
            index.next = reverseRange(nextNode, k);
            return reverseAfterHead;
        }

        public ListNode reverse(ListNode pre, ListNode after,int k) {
            if (k <= 0) {
                return pre;
            }
            if (after == null) {
                return pre;
            }
            ListNode next = after.next;
            after.next = pre;
            return reverse(after, next, k - 1);
        }
    }
}
