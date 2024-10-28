package cn.whaifree.leetCode.Array;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 12:45
 * @注释
 */
public class LeetCode86 {

    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 4, 3, 2, 5, 2});
        ListNode listNode1 = new Solution().partition(listNode, 3);
        listNode1.printList();
    }

    /**
     *
     */

    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode pre = new ListNode(-1, head);
            ListNode small = new ListNode(-1);
            ListNode big = new ListNode(-1);
            ListNode smallIndex = small;
            ListNode bigIndex = big;
            ListNode index = head;
            while (index != null) {
                if (index.val < x) {
                    smallIndex.next = index;
                    smallIndex = smallIndex.next;
                } else {
                    bigIndex.next = index;
                    bigIndex = bigIndex.next;
                }
                index = index.next;
            }
            smallIndex.next = big.next;
            bigIndex.next = null;
            return small.next;
        }
    }
}
