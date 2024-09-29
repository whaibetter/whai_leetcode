package cn.whaifree.redo.redo.redo_24_4_6;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 12:15
 * @注释
 */
public class LeetCode21 {

    @Test
    public void test() {
        new Solution1().mergeTwoLists(ListNode.listNodeFromArray(new int[]{1, 2, 4}), ListNode.listNodeFromArray(new int[]{1, 3, 4})).printList();

    }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }else if (list2 == null) {
                return list1;
            }else if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }

    class Solution1 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode preHead = new ListNode(-1);
            ListNode preIndex = preHead;
            ListNode indexA = list1, indexB = list2;
            while (indexA != null && indexB != null) {
                if (indexA.val < indexB.val) {
                    preIndex.next = indexA;
                    indexA = indexA.next;
                }else {
                    preIndex.next = indexB;
                    indexB = indexB.next;
                }
                preIndex = preIndex.next;
            }
            if (indexA != null) {
                preIndex.next = indexA;
            } else if (indexB != null) {
                preIndex.next = indexB;
            }

            return preHead.next;
        }
    }
}
