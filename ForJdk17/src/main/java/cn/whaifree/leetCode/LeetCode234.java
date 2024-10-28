package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

public class LeetCode234 {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("1", 1);
        }

        CountDownLatch countDownLatch = new CountDownLatch(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.forEach(
                        new BiConsumer<String, Integer>() {
                            @Override
                            public void accept(String s, Integer integer) {
                                System.out.println(s + ":" + integer);
                            }
                        }
                );
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    map.put("i" + i, i);
                    countDownLatch.countDown();
                }
            }
        }).start();

        countDownLatch.await();
        System.out.println(map.size());
    }


    @Test
    public void test()
    {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 3, 3,2, 1});
        System.out.println(new Solution().isPalindrome(listNode));
    }
    /**
     * 1. 截取一半
     * - 逆转、对比
     * 2. 输出到list中，再回问判断
     * 3.
     */

    class Solution {
        public boolean isPalindrome(ListNode head) {
            int len = 0;
            ListNode index = head;
            while (index != null) {
                len++;
                index = index.next;
            }

            index = head;
            for (int i = 0; i < len / 2; i++) {
                index = index.next;
            }

            ListNode newHead = reverseList(index);


            ListNode A = head;
            ListNode B = newHead;
            while (B != null) {
                if (A.val != B.val) {
                    return false;
                }
                A = A.next;
                B = B.next;
            }
            return true;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

    }
}
