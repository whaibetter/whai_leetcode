package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/4 0:24
 * @注释
 */
public class LeetCode82 {

    @Test
    public void test() {
        new Solution().deleteDuplicates(ListNode.listNodeFromArray(new int[]{1,2,3,3,4,4,5,5})).printList();
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode pre = new ListNode(Integer.MAX_VALUE, head);
            ListNode preIndex = pre;
            ListNode index = pre.next;
            while (index!= null) {
                if (index.next != null && index.next.val == index.val) {
                    while (index.next != null && index.next.val == index.val) {
                        index = index.next;
                    }
                    preIndex.next = index.next;
                    index = preIndex.next;
                } else {
                    preIndex = index;
                    index = index.next;
                }
            }
            return pre.next;
        }
    }
}
