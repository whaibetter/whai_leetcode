package cn.whaifree.redo.redo24_1_7;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

// 递归逆转链表
public class LeetCode206_false {

    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1,2,3,4,5});
        ListNode listNode1 = new Solution().reverseList(listNode);
        ListNode.printList(listNode1);
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            return reverse(null, head);
        }

        public ListNode reverse(ListNode pre, ListNode cur) {
            if (cur == null) {
                return pre;
            }
            // 只考虑三个点，1 2逆转，3（tmp 2.next）为下次递归的输入
            ListNode tmp = cur.next;
            cur.next = pre;
            return reverse(cur, tmp);
        }

    }
}
