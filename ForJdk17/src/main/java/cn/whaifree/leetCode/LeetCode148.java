package cn.whaifree.leetCode;

import org.junit.Test;
import cn.whaifree.leetCode.model.ListNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 14:35
 * @注释
 */
public class LeetCode148 {

    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{-1,5,3,4,0});
        ListNode listNode1 = new Solution().sortList(listNode);
        listNode1.printList();

    }

    class Solution {
        /**
         * 冒泡
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {
            ListNode resList = new ListNode(-1);

            ListNode tmpHead = new ListNode(-1, head);
            ListNode pre = tmpHead;


            ListNode index = pre; // 用于对比index.next和index.next的指针
            ListNode markMaxPre = pre; // 用于标记一次循环的最大值
            while (index.next != null) {
                // 一次循环找到最大的一个
                while (index.next != null) {
                    if (markMaxPre.next.val < index.next.val) {
                        markMaxPre = index;
                    }
                    index = index.next;
                }
                ListNode thisMax = markMaxPre.next;

                markMaxPre.next = markMaxPre.next.next;
                addToHead(resList, thisMax);
                index = pre;
                markMaxPre = pre;
            }

            return resList.next;
        }

        public void addToHead(ListNode pre, ListNode newNode) {
            if (newNode == null) {
                return;
            }
            ListNode next = pre.next;
            pre.next = newNode;
            newNode.next = next;
        }
    }

    @Test
    public void test2() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{4,2,1,3});
        ListNode listNode1 = new Solution2().sortList(listNode);
        listNode1.printList();
    }


    /**
     * 归并排序
     */

    class Solution2 {
        // 我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head.next; // 注意不要一起开始
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode BHead = slow.next;
            slow.next = null;
            ListNode sortAfterA = sortList(head);
            ListNode sortAfterB = sortList(BHead);

            return merge(sortAfterA, sortAfterB);
        }

        public ListNode merge(ListNode A, ListNode B) {
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            while (A != null && B != null) {
                if (A.val < B.val) {
                    cur.next = A;
                    A = A.next;
                } else {
                    cur.next = B;
                    B = B.next;
                }
                cur = cur.next;
            }
            cur.next = A != null ? A : B;
            return dummy.next;
        }
    }

}
