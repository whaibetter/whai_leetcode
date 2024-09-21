package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 14:11
 * @注释
 */
public class LeetCode92 {

    @Test
    public void test() {
        new Solution().reverseBetween(ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5}
        ), 1, 5).printList();
    }

    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (left == right) {
                return head;
            }
            ListNode pre = new ListNode(-1, head);
            ListNode tmpLeft = pre;
            ListNode tmpRight = pre;
            for (int i = 0; i < left - 1; i++) {
                tmpLeft = tmpLeft.next;
                tmpRight = tmpRight.next;
            }
            for (int i = 0; i < right - left + 2; i++) {
                tmpRight = tmpRight.next;
            }

            ListNode tmpNext = tmpLeft.next;
            tmpLeft.next = reverse(null, tmpNext, right - left + 1);

            tmpNext.next = tmpRight;

            return pre.next;
        }


        public ListNode reverse(ListNode pre, ListNode after, int k) {
            if (k == 0) {
                return pre;
            }
            if (after == null) {
                return pre;
            }
            ListNode tmp = after.next;
            after.next = pre;
            return reverse(after, tmp, k - 1);
        }

    }

    @Test
    public void test2() {
        ListNode pre = ListNode.listNodeFromArray(new int[]{1, 2, 3});
        new Solution().reverse(null, pre, 3).printList();
    }
}
