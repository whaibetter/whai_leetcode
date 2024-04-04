package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/4 13:05
 * @注释
 */
public class LeetCode21 {


    @Test
    public void test() {
        new Solution1().mergeTwoLists(
                ListNode.listNodeFromArray(new int[]{5}),
                ListNode.listNodeFromArray(new int[]{1, 2, 4})
        ).printList();
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list2 == null) {
                return list1;
            }
            if (list1 == null) {
                return list2;
            }

            ListNode pre = new ListNode(-1); // 标记结果头部
            ListNode preIndex = pre; // 标记需要添加的位置
            // 合并有序链表
            ListNode indexA = list1;
            ListNode indexB = list2;
            while (indexA != null && indexB != null) {
                if (indexA.val <= indexB.val) {
                    preIndex.next = indexA;
                    indexA = indexA.next;
                }else {
                    preIndex.next = indexB;
                    indexB = indexB.next;
                }
                preIndex = preIndex.next;
            }

            if (indexA == null) {
                preIndex.next = indexB;
            }else {
                preIndex.next = indexA;
            }

            return pre.next;
        }
    }


    class Solution1 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }

        }
    }
}
