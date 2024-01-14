package cn.whaifree.redo.redo24_1_7;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

public class LeetCode2487_false {

    @Test
    public void test() {
        ListNode head = ListNode.listNodeFromArray(new int[]{5, 2, 13, 3, 8});
        ListNode reverse = new Solution().removeNodes(head);
        reverse.printList();

    }

    /**
     *
     * 移除每个右侧有一个更大数值的节点。
     * [5,2,13,3,8]
     * 输出：[13,8]
     *
     * - 递减的
     *
     *
     *
     */
    class Solution {
        public ListNode removeNodes(ListNode head) {
            // 逆转 8 3 13 2 5
            head = reverse(head);
            // 删除全部比当前点小的值
            ListNode index = head;
            ListNode maxNode = head;
            while (index.next != null) {
                if (maxNode.val > index.next.val) {
                    index.next = index.next.next;
                } else {
                    index = index.next;
                    maxNode = index;
                }
            }

            return head;
        }


        // 1 2 3 4 5
        public ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode index = head;
            ListNode tmp = null;
            while (index != null) {
                tmp = index.next;
                index.next = pre;
                pre = index;
                index = tmp;
            }
            return pre;
        }

    }
}
