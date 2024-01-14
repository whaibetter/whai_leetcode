package cn.whaifree.redo.redo_24_1_13;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/14 12:24
 * @注释
 */
public class LeetCode2487 {

    @Test
    public void test() {
        ListNode head = ListNode.listNodeFromArray(new int[]{5, 2, 13, 1, 3, 8});
        new Solution().removeNodes(head).printList();
    }

    class Solution {
        public ListNode removeNodes(ListNode head) {
            // 逆转
            ListNode newHead = reverse(head);

            // 移除左边有更小的节点
            remove(newHead);

            // 再逆转
            return reverse(newHead);
        }

        public void remove(ListNode head) {
            ListNode index = head;
            ListNode targetNode = head;
            while (index.next != null) {
                if (index.next.val < targetNode.val) {
                    index.next = index.next.next;
                }else {
                    index = index.next;
                    targetNode = index;
                }
            }
        }

        public ListNode reverse(ListNode head) {

            ListNode pre = null;
            ListNode index = head;
            while (index != null) {
                ListNode tmp = index.next;
                index.next = pre;
                pre = index;
                index = tmp;
            }
            return pre;
        }
    }

}
