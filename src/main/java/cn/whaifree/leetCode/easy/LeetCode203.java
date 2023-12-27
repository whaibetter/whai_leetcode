package cn.whaifree.leetCode.easy;


import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * 链表
 *
 * 203. 移除链表元素
 * 示例 1：

 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 *
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 */
public class LeetCode203 {


//    /**
//     * [6] 6
//     * []  6
//     * [2] 6
//     *
//     *
//     * @param head
//     * @param val
//     * @return
//     */
//    public ListNode removeElements(ListNode head, int val) {
//
//        // 如果链表只有一个节点或者没有要删除的元素，则直接返回原链表
//        if (head == null) {
//            return head;
//        }
//        if (head.next == null && head.val == val) {
//            head = null;
//            return head;
//        } else if (head.next == null) {
//            return head;
//        }
//
//        // 定义一个指针pre指向head节点
//        // 定义一个指针index指向head.next节点
//        ListNode pre = head;
//        ListNode index = head.next;
//
//        // 遍历链表，直到index.next为null
//        while (index.next != null) {
//
//            // 如果index节点的值等于要删除的元素val
//            if (index.val == val) {
//
//                // 删除该节点
//                pre.next = index.next;
//
//                // 将指针index移动到下一个节点
//                index = pre.next;
//
//                // 将指针pre移动到下一个节点
//                pre = pre.next;
//
//                // 继续遍历链表
//                continue;
//            }
//
//            // 如果index节点的值不等于要删除的元素val
//            index = index.next;
//            pre = pre.next;
//        }
//        // 尾巴节点为val，那就删除尾巴节点
//        if (index.val == val) {
//            pre.next = null;
//        }
//        // 如果头节点的值等于要删除的元素val
//        if (head.val == val) {
//            head = head.next;
//        }
//
//
//        // 返回删除元素后的链表
//        return head;
//    }


    public ListNode removeElements1(ListNode head, int val) {
        // 找到第一个head不删除的点 删除头结点时另做考虑
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 保证删完后不为空
        if(head==null)
            return head;
        //
        ListNode index = head;

        while (index.next != null) {
            if (index.next.val == val) {
                index.next = index.next.next;
            } else {
                index = index.next;
            }
        }
        return head;
    }

//    /**
//     * 递归
//     * @param head
//     * @param val
//     * @return
//     */
//    public ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return null;
//        }
//        head.next = removeElements(head.next, val);
//        if (head.next.val == val) {
//            return head.next.next;
//        } else {
//            return head.next;
//        }
//    }


    /**
     * 递归
     * 1. 停止条件
     * 2. 循环返回值
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        //   h
        // 6 6
        // 让head 之后的元素交给下个递归
        head.next = removeElements(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }

        // 递归的返回值为head.next,即传入的下一结点；
        // 如果匹配就返回当前结点；不匹配，返回的head就是前一结点了。
        // 压栈时的head.next为后一个结点；弹栈时的head.next就位后前一个结点
    }

    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        // 1. 获取当前节点
        // 2. 递归next 6 6
        head.next = removeElements3(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }




    @Test
    public void Test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{6,6,6,6});
//        ListNode.printList(listNode);
        ListNode listNode1 = removeElements(listNode, 6);
        ListNode.printList(listNode1);
    }
}
