package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 11:06
 * @注释
 */
public class LeetCode160 {

    @Test
    public void test()
    {

    }

    class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode A = headA, B = headB;
            while (A != B) {
                A = A == null ? headB : A.next;
                B = B == null ? headA : B.next;
            }
            return A;
        }
    }
}
