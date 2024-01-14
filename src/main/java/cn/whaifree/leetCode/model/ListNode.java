package cn.whaifree.leetCode.model;


public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

    /**
     * 将给定的整数数组转换为链表，并返回链表的头节点。
     */
    public static ListNode listNodeFromArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        ListNode prev = dummy;

        for (int num : nums) {
            ListNode node = new ListNode(num);
            prev.next = node;
            prev = node;
        }

        return dummy.next;
    }

    /**
     * 输出链表的函数
     * @param head 链表的头节点
     */
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null!");
        }
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    public void printList() {
        printList(this);
    }

}
