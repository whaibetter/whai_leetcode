package cn.whaifree.interview.qiuzhao;

import cn.whaifree.leetCode.model.ListNode;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/24 9:57
 * @注释
 */
public class meituan824 {



}

class mtp1{


    /**
     * 小美初始位于（a，b)位置，二维平面上有n个瓶子，每个瓶子的位置为（x，y），小美每次可以向上、下、左、石移动一格，每次移动的代价为 1，
     * 小美需要每次移动到一个瓶子的位置上，然后拿起瓶子把它放到(c,d) 位置，每次最多只能拿一个瓶子。请问最少需要多少代价才能把所有瓶子都放到（c,d）位置上。
     * @param args
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int startX = in.nextInt();
        int startY = in.nextInt();
        int targetX = in.nextInt();
        int targetY = in.nextInt();
        int num = in.nextInt();
        int res = 0;
        for (int i = 0; i < num; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            res += go(startX, startY, x, y);
            res += go(x, y, targetX, targetY);
            startX = targetX;
            startY = targetY;
        }
        System.out.println(res);

    }

    public static int go(int startX, int startY, int endX, int endY) {
        int res = 0;
        res += Math.abs(endX - startX);
        res += Math.abs(endY - startY);
        return res;
    }

}

class mtp2{


    /**
     * 小美有三个数字a，b,c，他每次操作可以选择一个数字将其加一k次，小美想知道a×bxc的最大值是多少？
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int min = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int[] nums = new int[3];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();


        for (int i = 0; i < k; i++) {
            int min = findMin(nums);
            nums[min] += 1;
        }


        System.out.println(((long) nums[0] * nums[1] * nums[2])%1000000007);

//        int tmpMax = 1;
//        for (int i = 0; i < nums.length; i++) {
//            tmpMax *= nums[i];
//        }
//
//        for (int j = 0; j < k; j++) {
//            int tmp = tmpMax;
//            int indexIncr = 0;
//            for (int i = 0; i < nums.length; i++) {
//                int num = nums[i];
//                int b = (tmpMax / num) * (num + 1);
//                if (tmp < b) {
//                    tmp = b;
//                    indexIncr = i;
//                }
//            }
//            tmpMax = Math.max(tmpMax, tmp);
//            nums[indexIncr] += 1;
//        }
//
//        System.out.println(tmpMax);

    }
}

class test {

    public static void main(String[] args) {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5, 6});
        ListNode.printList(rm(listNode, 6));
    }


    public static ListNode rm(ListNode head, int n) {
        ListNode pre = new ListNode(-1, head);
        ListNode after = pre;
        for (int i = 0; i < n; i++) {
            after = after.next;
        }
        ListNode preTmp = pre;

        while (after.next != null) {
            after = after.next;
            preTmp = preTmp.next;
        }

        preTmp.next = preTmp.next.next;

        return pre.next;
    }
}
