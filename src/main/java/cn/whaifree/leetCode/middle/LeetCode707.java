package cn.whaifree.leetCode.middle;

import org.junit.Test;

/**
 * 707. 设计链表
 * 中等
 * 相关标签
 * 相关企业
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 *
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 *
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 *
 * 实现 MyLinkedList 类：
 *
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 *
 *
 * 示例：
 *
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 *
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3
 */
public class LeetCode707 {

    @Test
    public void test() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(4);
        myLinkedList.get(1);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(5);
        myLinkedList.deleteAtIndex(3);
        myLinkedList.addAtHead(7);
        myLinkedList.get(3);
        myLinkedList.get(3);
        myLinkedList.get(3);

        myLinkedList.addAtHead(1);
        myLinkedList.deleteAtIndex(4);
    }

}

class ListNode1{
    int val;
    ListNode1 next;

    public ListNode1() {
    }

    public ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 过了 但效果不好
 *
 * 执行用时分布11ms
 * 击败12.61%使用 Java 的用户
 *
 * 消耗内存分布44.49MB
 * 击败5.02%使用 Java 的用户
 */
class MyLinkedList1 {

    ListNode1 head;
    public MyLinkedList1() {
        head = null;
    }

    // 0
    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        ListNode1 node = head;
        int i = 0;
        while (node != null) {
            if (i++ == index) {
                return node.val;
            }
            node = node.next;
        }
        return -1;
    }

    public void addAtHead(int val) {
        head = new ListNode1(val, head);
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new ListNode1(val, null);
            return;
        }
        ListNode1 node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new ListNode1(val, null);
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        ListNode1 node = head;
        int i = 0;
        while (node != null) {
            if (i++ == index - 1) {
                node.next = new ListNode1(val, node.next);
                return;
            }
            node = node.next;
        }
    }

    public void deleteAtIndex(int index) {
        if (index == 0) {
            // 头删
            head = head.next;
            return;
        }
        ListNode1 node = head;
        int i = 0;
        while (node != null) {
            if (i++ == index - 1 && node.next != null) {
                node.next = node.next.next;
            }
            node = node.next;
        }
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 执行用时分布9ms
 * 击败88.60%使用 Java 的用户
 *
 * 消耗内存分布44.44MB
 * 击败5.02%使用 Java 的用户
 */
class MyLinkedList {

    int size;
    ListNode head;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }
        ListNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        head = new ListNode(val, head);
        size++;
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            // 头插
            head = new ListNode(val, head);
            size++;
            return;
        }
        if (index < 0 || index > size) {
            return;
        }

        ListNode node = head;
        int i = 0;
        while (i++ < index - 1) {
            node = node.next;
        }
        node.next = new ListNode(val, node.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            return;
        }
        if (index == 0) {
            // 头删
            head = head.next;
            size--;
            return;
        }

        ListNode node = head;
        int i = 0;
        while (i++ < index - 1) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
