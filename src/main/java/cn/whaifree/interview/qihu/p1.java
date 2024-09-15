package cn.whaifree.interview.qihu;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class p1 {


}

class Main {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int num = cin.nextInt();
        int can = cin.nextInt();
        int[][] custom = new int[num][2];
        for (int i = 0; i < num; i++) {
            custom[i][0] = cin.nextInt();
            custom[i][1] = cin.nextInt();
        }

        used = new boolean[can + 1];

        getMaxSatisfied(custom, 0);
        System.out.println(max);
    }

    static int now = 0;
    static int max = Integer.MIN_VALUE;
    static boolean[] used = null;

    public static void getMaxSatisfied(int[][] custom, int start) {
        if (start >= custom.length) {
            max = Math.max(max, now);
        }

        for (int i = start; i < custom.length; i++) {
            int[] ints = custom[i];
            int a = ints[0];
            int b = ints[1];
            if (!used[a] && !used[b]) {
                now++;
                used[a] = true;
                used[b] = true;
                getMaxSatisfied(custom, i + 1);
                used[a] = false;
                used[b] = false;
                now--;
            }
        }

    }
}

class p323 {
    public static void main(String args[]) {

        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        for (int i = 0; i < n; i++) {
            String next = cin.next();
            System.out.println(cal(next) ? "Yes" : "No");
        }

    }

    public static boolean cal(String s) {
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= 9; j++) {
                StringBuilder stringBuilder = new StringBuilder(s);
                stringBuilder.insert(i, j);
                String[] split = stringBuilder.toString().split("=");
                int z1 = calVal(split[0]);
                int z2 = calVal(split[1]);
                if (z1 == z2) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 6
     * 16=1+2*3
     * 7*8*9=54
     * 1+1=1+22
     * 4*6=22+2
     * 15+7=1+2
     * 11+1=1+5
     */


    public static int calVal(String s) {
        Deque<Integer> deque = new LinkedList<>();
        int res = 0;
        char[] charArray = s.toCharArray();
        char pre = ' ';
        for (int i = 0; i < charArray.length; i++) {
            StringBuilder str = new StringBuilder();
            while (i < charArray.length && charArray[i] >= '0' && charArray[i] <= '9') {
                str.append(charArray[i]);
                i++;
            }
            int num = Integer.parseInt(str.toString());
            if (pre == '+') {
                deque.push(num);
            } else if (pre == '-') {
                deque.push(-num);
            } else if (pre == '*') {
                deque.push(deque.pop() * num);
            } else {
                deque.push(num);
            }
            if (i < charArray.length) {
                pre = charArray[i];
            }
        }

        while (!deque.isEmpty()) {
            res += deque.pop();
        }
        return res;
    }
}


class MeidiT1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int i = in.nextInt();
        System.out.println(isAbsSS(i));
    }

    public static boolean isAbsSS(Integer integer) {
        if (isSS(integer)) {
            String s = String.valueOf(integer);
            String string = new StringBuilder(s).reverse().toString();
            return isSS(Integer.parseInt(string));
        } else {
            return false;
        }
    }

    public static boolean isSS(Integer integer) {
        if (integer < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(integer); i++) {
            if (integer % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class MeidiT2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < in.nextInt(); i++) {
            int treeCount = in.nextInt();
            int[] height = new int[treeCount];
            int[] l = new int[treeCount];
            int[] r = new int[treeCount];
            for (int j = 0; j < treeCount; j++) {
                l[i] = in.nextInt();
                r[i] = in.nextInt();
                height[i] = in.nextInt();
            }


        }
    }

    public static void method(int[] l, int[] r, int[] height) {
        int opr = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {
            if (height[i] > r[i]) {
                int excess = height[i] - r[i];
                opr++;

                while (i < n && height[i] > r[i]) {
                    height[i] -= excess;
                    i++;
                }

                i--; // for

            }
        }
    }
}

class MeidiT3 {

    static HashMap<Long, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        int i = in.nextInt();
        for (int i1 = 0; i1 < i; i1++) {
            method(in.nextLong());
        }
    }

    public static void method(long num) {
        long cnt = countOne(num);
        long target = 2 * cnt;
        long x = 0;
        while (countOne(x | num) != target) {
            x++;
        }
        System.out.println(x | num);
    }

    public static long countOne(long num) {
        long count = 0;
        while (num != 0) {
            num &= (num - 1);
            count++;
        }
        return count;
    }
}