package cn.whaifree.interview.mhy;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 15:02
 * @注释
 */
public class Main
{
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int i = cin.nextInt();
        int[] nums = new int[i];
        for (int i1 = 0; i1 < nums.length; i1++) {
            nums[i1] = cin.nextInt();
        }
        method(nums);


    }

    public static void method(int[] nums) {
        int[] tmp = Arrays.copyOf(nums, nums.length);

        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int a = i;
            int b = i + 1;
            if (nums[a] < 0 && nums[b] > 0) {
                nums[a] = -nums[a];
                nums[b] = -nums[b];
            }
            if (nums[a] < 0 && nums[b] < 0) {
                nums[a] = -nums[a];
                nums[b] = -nums[b];
            }
            sum += nums[a];
        }
        sum += nums[nums.length - 1];

        nums = tmp;
        int sumB = 0;
        for (int i = tmp.length - 1; i > 0; i--) {
            int a = i;
            int b = i - 1;
            if (nums[a] < 0 && nums[b] > 0) {
                nums[a] = -nums[a];
                nums[b] = -nums[b];
            }
            if (nums[a] < 0 && nums[b] < 0) {
                nums[a] = -nums[a];
                nums[b] = -nums[b];
            }
            sumB += nums[a];
        }
        sumB += nums[0];


        System.out.println(Math.max(sumB, sum));
    }
}

class p3{
    public static void main(String[] args) {

        LinkedList<Integer> lis = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            lis.add(i, i * i);
        }

        System.out.println(lis.indexOf(4));

    }
}

class P2{
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int i = cin.nextInt();
        LinkedList<Integer> lis = new LinkedList<>();
        for (int i1 = 0; i1 < i; i1++) {
            lis.add(1 + i1);
        }
        int x = cin.nextInt();
        for (int j = 0; j < x; j++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            int c = cin.nextInt();
            method(lis, a, b, c);
        }

        for (Integer li : lis) {
            System.out.println(li+" ");
        }
    }

    public static void method(LinkedList<Integer> list, int a, int b, int c) {
        Integer A = list.get(list.indexOf(a));
        list.remove(a - 1);
        int i = list.indexOf(b);

        int indexOfB = i;
        if (c == 0) {
            list.add(indexOfB, A);
        }else {
            list.add(indexOfB + 1, A);
        }
    }
}
