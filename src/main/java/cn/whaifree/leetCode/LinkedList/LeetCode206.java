package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.Test;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * 输入：head = []
 * 输出：[]

 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 */
public class LeetCode206 {

    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1,2,3,4,5});
        ListNode listNode1 = reverseList2(listNode);
        ListNode.printList(listNode1);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    /**
     * 使用栈
     * @param head
     * @return
     */
     public ListNode reverseList(ListNode head) {
         Stack<Integer> integerStack = new Stack<>();
         ListNode index = head;
         while (index!= null) {
             integerStack.add(index.val);
             index = index.next;
         }

         ListNode ansHead = new ListNode();
         index = ansHead;
         while (!integerStack.empty()) {
             index.next = new ListNode(integerStack.pop());
             index = index.next;
         }
         return ansHead.next;
     }


    /**
     * <img src='https://code-thinking.cdn.bcebos.com/gifs/206.%E7%BF%BB%E8%BD%AC%E9%93%BE%E8%A1%A8.gif' />
     *
     * 使用三个指针 pre index tmp <br/>
     * - 存储tmp为index的下一个节点，让index指向pre。 <br/>
     *   pre index tmp <br/>
     *   1 <-- 2    3 -->  4 <br/>
     *        pre  index   tmp<br/>
     *   1 <-- 2    3 -->  4<br/>
     *        pre  index   tmp<br/>
     *   1 <-- 2 <-- 3     4<br/>
     *
     *  <b>移动指针不一定要index.next, 在指针固定顺序的时候 <br/>
     *  可以让tmp=index.next;pre=index;index=tmp</b>
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode tmp = null;
        ListNode index = head;
        while (index != null) {
            tmp = index.next;
            index.next = pre;
            pre = index;
            index = tmp;
        }
        return pre;
    }


    /**
     * 递归
     * - 递归 只考虑当前这个部分，<b>把其他的部分扔给下一次递归</b>
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        // 两个逆转
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
