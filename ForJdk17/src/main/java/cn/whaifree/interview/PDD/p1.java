package cn.whaifree.interview.PDD;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/13 14:59
 * @注释
 */
public class p1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        int i = in.nextInt();
        int[] nums = new int[i];
        for (int j = 0; j < i; j++) {
            nums[j] = in.nextInt();
        }
        System.out.println(method(nums));
    }

    public static int method(int[] nums) {
        int res = 0;
        int pro = nums[0];
        while (pro % 10 == 0) {
            res++;
            pro /= 10;
        }
        for (int i = 1; i < nums.length; i++) {
            pro *= nums[i];
            while (pro % 10 == 0) {
                res++;
                pro /= 10;
            }
        }
        return res;
    }

}

class p2{

    public static void main(String[] args) {

        /**
         * abcd
         * abzc
         * b
         * zyxwvutsrqponmlkjihgfedcba
         * abcdefghijklmnopqrstuvx
         *
         * 5
         * abc
         * abz
         * azyxwvutsrqponmlkjihgfedcb
         * zyxwvutsrqponmlkjihgfedcba
         * abcdefghijklmnopqrstuvwzyx
         *
         */
//        next("zyxwvutsrqponmlkjihgfedcba");

        next("abc");

        next("abcdefghijklmnopqrstuvwzyx");

//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int i = in.nextInt();
//            for (int i1 = 0; i1 < i; i1++) {
//                String next = in.next();
//                System.out.println();
//            }
//        }
    }


    public static void next(String s) {
        char[] charArray = s.toCharArray();
        int i = charArray.length - 2;
        while (i >= 0 && charArray[i] >= charArray[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = charArray.length - 1;
            while (j >= 0 && charArray[i] > charArray[j]) {
                j--;
            }
            swap(charArray, i, j);
        }
//        reverse(charArray, i + 1, charArray.length - 1);


        System.out.println(new String(Arrays.copyOf(charArray, i + 1)));

    }


    public static void reverse(char[] chars, int x, int y) {
        while (x < y) {
            swap(chars, x++, y--);
        }
    }

    public static void swap(char[] chars, int x, int y) {
        char tmp = chars[x];
        chars[x] = chars[y];
        chars[y] = tmp;
    }

}

class p3{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            int i = in.nextInt();
            for (int i1 = 0; i1 < i; i1++) {
                int count = in.nextInt();
                int split = in.nextInt();
                long[] ints = new long[count];
                for (int j = 0; j < count; j++) {
                    ints[j] = in.nextLong();
                }
                System.out.println(in(ints, split) ? "True" : "False");
            }
        }

    }


    /**
     * 长度为n的数组，划分为k个子串
     * 调整子串的顺序，组成一个新的数组
     * 判断数组和划分数k，能否得到一个递增的序列
     * @param nums
     * @param split
     */
    public static boolean in(long[] nums, int split) {
        List<int[]> spl = new LinkedList<>();
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[right - 1] > nums[right]) {
                spl.add(new int[]{left, right - 1});
                left = right;
            }
            right++;
        }
        spl.add(new int[]{left, right - 1});
        if (spl.size() > split) {
            return false;
        }

        Collections.sort(spl, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int) (nums[o1[0]] - nums[o2[0]]) > 0 ? 1 : -1;
            }
        });
        // Comparison method violates its general contract
        for (int i = 1; i < spl.size(); i++) {
            int[] before = spl.get(i - 1);
            int[] me = spl.get(i);
            if (nums[before[1]] > nums[me[0]]) {
                return false;
            }
        }

        return true;
    }
}
