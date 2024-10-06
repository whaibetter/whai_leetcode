package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 12:07
 * @注释
 */
public class LeetCode92  {

    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5});

        ListNode listNode1 = new Solution().reverseBetween(listNode, 1, 5);
        listNode1.printList();

    }

    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode pre = new ListNode(-1, head);
            ListNode index = pre;
            for (int i = 0; i < left - 1; i++) {
                index = index.next;
            }

            ListNode revHead = index.next;
            ListNode indexB = pre;
            for (int i = 0; i < right; i++) {
                indexB = indexB.next;
            }
            ListNode revTail = indexB;
            ListNode after = indexB.next;
            // 逆转revHead到revTail

            ListNode reverse = reverse(null, revHead, right - left + 1);
            index.next = reverse;
            revHead.next = after;
            return pre.next;
        }

        public ListNode reverse(ListNode pre, ListNode after,int k) {
            if (after == null) {
                return pre;
            }
            if (k == 0) {
                return pre;
            }
            ListNode tmp = after.next;
            after.next = pre;
            return reverse(after, tmp, k - 1);
        }

    }
}
