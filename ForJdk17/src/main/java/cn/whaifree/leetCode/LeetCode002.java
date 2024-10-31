package cn.whaifree.leetCode;

import cn.whaifree.leetCode.model.ListNode;

import java.util.ArrayList;

/**
 *
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/13 21:18
 */
public class LeetCode002 {


    /**
     * l1和l2向下移动，让答案节点的值为l1+l2+carry，carry表示上一节点所带来的增1
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode ansNode=new ListNode(0);
        ListNode ansIndex = ansNode;
        int carry = 0;
        while (l1!=null||l2!=null||carry!=0){
            //如果l1和l2都空了，但carry为1，还是要多一个1节点的。
            int val1 = l1==null?0:l1.val;
            int val2 = l2==null?0:l2.val;
            int nodeVal = val1 + val2 + carry;

            carry = nodeVal / 10;
            ansIndex.next = new ListNode(nodeVal % 10);
            ansIndex = ansIndex.next;

            //一旦l1为空，直接把l2接上
            if (l1!=null){
                l1 = l1.next;
            }
            //l2为空，把l1接上
            if (l2!=null){
                l2 = l2.next;
            }
        }

        return ansNode.next;



    }


    /**
     * 从坐到右挨个相加，
     * val%10 取得余数
     * val//10 取得整数十位
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode ansNode = new ListNode(0);
        ListNode ansIndex = ansNode;
        ListNode index1 = l1;
        ListNode index2 = l2;
        boolean flag = false;
        while (index1 != null || index2 != null) {
            //两个指针都非空的时候表示需要计算往下加。
            int val1 = index1.val;
            int val2 = flag?index2.val+1:index2.val;
            flag = false;
            if (val1+val2<10){
                //无需进1
                ansIndex.next = new ListNode(val1+val2);
            }else {
                //需要进1
                ansIndex.next = new ListNode((val1+val2)-10);
                flag = true;
            }

            index1 = index1.next;
            index2 = index2.next;

        }

        //直接追加
        if (index1 != null) {
            ansIndex.next = index1;
        }else {
            ansIndex.next = index2;
        }



        return ansNode.next;

    }

}


//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

class RefCountGC{
    // 这个成员属性的唯一作用就是占用一点内存
    private byte[] bigSize = new byte[5*1024*1024];
    // 引用
    Object reference = null;

    byte[]buffer = new byte[1*1024*1024];//1MB
    public static void main(String[] args) {
        ArrayList<RefCountGC> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new RefCountGC());
                count++;
            }
        }catch(Throwable e){
            System.out.println("count：" + count);
            e.printStackTrace();
        }
    }
}
