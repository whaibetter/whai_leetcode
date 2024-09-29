package cn.whaifree.leetCode.LinkedList;

import org.junit.Test;

public class LeetCode707_2 {

    @Test
    public void test() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(3);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtHead(5);
//        myLinkedList.addAtIndex(4, 99);
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(2));
        System.out.println(myLinkedList.get(3));
        System.out.println(myLinkedList.get(4));
        System.out.println(myLinkedList.get(-1));

//        myLinkedList.deleteAtIndex(4);
    }

    class ListNode {
        ListNode pre;
        ListNode next;
        int val;

        public ListNode(ListNode pre, ListNode next, int val) {
            this.pre = pre;
            this.next = next;
            this.val = val;
        }
    }


    class MyLinkedList {
        ListNode head;
        ListNode tail;
        int size;

        public MyLinkedList() {
            head = new ListNode(null, null, 0);
            tail = new ListNode(head, null, 0);
            head.next = tail;
            size = 0;
        }

        public int get(int index) {
            if (index >= size || index < 0) {
                return -1;
            }
            if (index <= size / 2) {
                ListNode trueIndex = head.next;
                for (int i = 0; i < index; i++) {
                    trueIndex = trueIndex.next;
                }
                return trueIndex.val;
            } else {
                ListNode trueIndex = tail.pre;
                for (int i = size - 1; i > index; i--) {
                    trueIndex = trueIndex.pre;
                }
                return trueIndex.val;
            }
        }

        public void addAtHead(int val) {
            ListNode listNode = new ListNode(head, head.next, val);
            head.next.pre = listNode;
            head.next = listNode;
            size++;
        }

        public void addAtTail(int val) {
            ListNode listNode = new ListNode(tail.pre, tail, val);
            tail.pre.next = listNode;
            tail.pre = listNode;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }

            if (index == 0) {
                addAtHead(val);
                return;
            }
            if (index == size) {
                addAtTail(val);
                return;
            }

            if (index <= size / 2) {
                ListNode trueIndex = head.next;
                for (int i = 0; i < index; i++) {
                    trueIndex = trueIndex.next;
                }
                ListNode listNode = new ListNode(trueIndex.pre, trueIndex, val);
                trueIndex.pre.next = listNode;
                trueIndex.pre = listNode;
            } else {
                ListNode trueIndex = tail.pre;
                for (int i = size - 1; i > index; i--) {
                    trueIndex = trueIndex.pre;
                }
                ListNode listNode = new ListNode(trueIndex.pre, trueIndex, val);
                trueIndex.pre.next = listNode;
                trueIndex.pre = listNode;
            }
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) {
                return;
            }
            if (index == 0) {
                head.next.next.pre = head;
                head.next = head.next.next;
                size--;
                return;
            }
            if (index == size - 1) {
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
                size--;
                return;
            }

            if (index < size / 2) {
                ListNode trueIndex = head.next;
                for (int i = 0; i < index; i++) {
                    trueIndex = trueIndex.next;
                }
                trueIndex.pre.next = trueIndex.next;
                trueIndex.next.pre = trueIndex.pre;

            } else {
                ListNode trueIndex = tail.pre;
                for (int i = size - 1; i > index; i--) {
                    trueIndex = trueIndex.pre;
                }
                trueIndex.next.pre = trueIndex.pre;
                trueIndex.pre.next = trueIndex.next;
            }
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
}


