package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/20 11:42
 * @注释
 */
public class LeetCode2 {

    @Test
    public void test() {
        new Solution1().addTwoNumbers(
                /*[9,9,9,9,9,9,9], l2 = [9,9,9,9]*/
                ListNode.listNodeFromArray(new int[]{9, 9, 9, 9, 9, 9, 9})
                , ListNode.listNodeFromArray(new int[]{9, 9, 9, 9})
        ).printList();
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return circle(l1, l2, false);
        }

        /**
         *
         * @param l1
         * @param l2
         * @param in 是否进1
         * @return
         */
        public ListNode circle(ListNode l1, ListNode l2, boolean in) {

            if (l1 == null && l2 == null && in) {
                return new ListNode(1);
            }

            if (l1 == null) {
                // l2 调用另一个递归方法，不断去判断是否需要在当前节点进1
                return judgeIn(l2, in);
            } else if (l2 == null) {
                return judgeIn(l1, in);
            }

            int val = 0;
            int sum = l1.val + l2.val;
            if (in) {
                val = sum + 1;
            }else {
                val = sum;
            }

            ListNode res = new ListNode(val % 10);
            res.next = circle(l1.next, l2.next, val >= 10);
            return res;
        }

        public ListNode judgeIn(ListNode node, boolean in) {
            if (node == null) {
                if (in) {
                    return new ListNode(1);
                }
                return null;
            }

            int val = 0;
            if (in) {
                val = node.val + 1;
            }else {
                val = node.val;
            }
            ListNode res = new ListNode(val % 10);
            res.next = judgeIn(node.next, val >= 10);
            return res;
        }
    }

    class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode indexA = l1;
            ListNode indexB = l2;
            boolean flagIn = false;
            while (indexA != null && indexB != null) {
                int sum = indexA.val + indexB.val;
                if (flagIn) {
                    sum += 1;
                }
                indexA.val = sum % 10;
                if (sum >= 10) {
                    flagIn = true;
                }else {
                    flagIn = false;
                }

                if (indexA.next == null && indexB.next == null && flagIn) {
                    indexA.next = new ListNode(1);
                    break;
                }

                if (indexA.next == null && indexB.next != null) {
                    indexA.next = new ListNode(0);
                }
                if (indexB.next == null && indexA.next != null) {
                    indexB.next = new ListNode(0);
                }


                indexB = indexB.next;
                indexA = indexA.next;
            }


            return l1;
        }

    }

}
