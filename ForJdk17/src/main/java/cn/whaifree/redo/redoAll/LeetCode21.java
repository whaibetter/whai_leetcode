package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/24 2:26
 * @注释
 */
public class LeetCode21 {

    @Test
    public void test() {

        new Solution().mergeTwoLists(
                ListNode.listNodeFromArray(new int[]{1, 2, 4}),
                ListNode.listNodeFromArray(new int[]{1, 3, 4})
        ).printList();
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode tmpHead = new ListNode(-1);
            ListNode index1 = list1;
            ListNode index2 = list2;
            ListNode index = tmpHead;
            while (index1 != null && index2 != null) {
                if (index1.val < index2.val) {
                    index.next = index1;
                    index1 = index1.next;
                }else {
                    index.next = index2;
                    index2 = index2.next;
                }
                index = index.next;
            }

            while (index1 != null) {
                index.next = index1;
                index1 = index1.next;
                index = index.next;
            }
            while (index2 != null) {
                index.next = index2;
                index2 = index2.next;
                index = index.next;
            }
            return tmpHead.next;

        }
    }
}
