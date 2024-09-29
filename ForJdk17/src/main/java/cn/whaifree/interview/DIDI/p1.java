package cn.whaifree.interview.DIDI;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/7 17:24
 * @注释
 */

class p4{


    static long[] gezi = null;
    static long max = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            int a = scanner.nextInt();
            long b = scanner.nextLong();
            gezi = new long[a];
            method(b, gezi, 0);
            System.out.println(max);
            max = Integer.MIN_VALUE;
        }
    }

    /**
     * @param gezi 格子
     * @param num  物品数
     * @return
     */
    public static void method(long num, long[] gezi,int start) {
        if (num == 0) {
            max = Math.max(cal(gezi), max);
            return;
        }

        for (int i = start; i < gezi.length; i++) {
            gezi[i] += 1;
            method(num - 1, gezi, i);
            gezi[i] -= 1;
        }
    }

    public static long cal(long[] gezi) {
        long res = 0;
        for (int i = 0; i < gezi.length - 1; i++) {
            res += Math.abs(gezi[i] - gezi[i + 1]);
        }
        return res;
    }

}
public class p1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
        }
    }

    /**
     * dp[i][j] 前i个格子放入j个收藏品的最大美观度
     *
     * dp[i][j] = max(dp[i-1][j-k]+(i-1)*k -k*(j-1))
     *   0 1 2 3
     * 0 0 0 0 0
     * 1 0 0 1 1
     * 2 0 0
     *
     *
     *  0 1 0
     *  0 3 0
     *  0 1 2
     *
     *
     * @param size 格子数量
     * @param num  物品数量
     */
    public static void method(int size, int num) {
        int[][] dp = new int[size + 1][num + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;


        for (int i = 1; i <= size; i++) {
            for (int j = 0; j <= num; j++) {
                for (int k = 0; k <= j; k++) {
                    //可选择的物品
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - k] + (i - 1) * k - k * (j - 1));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < num; i++) {
            max = Math.max(max, dp[size][i]);
        }
        System.out.println(max);

    }

    @Test
    public void test() {
        method(2, 2);
    }


}


class p3{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int type = scanner.nextInt();
        int[][] costs = new int[type][type];
        for (int i = 0; i < type; i++) {
            for (int j = 0; j < type; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }
        String next = scanner.next();
        path = new StringBuilder(next);
        remove(costs);
        System.out.println(maxCost);
    }

    static StringBuilder path = null;

    static int tmpCost = 0;
    static int maxCost = Integer.MIN_VALUE;

    public static void remove(int[][] map) {
        if (path.isEmpty()) {
            maxCost = Math.max(maxCost, tmpCost);
            return;
        }

        for (int i = 0; i < path.length() / 2; i++) {
            StringBuilder tmpPath = new StringBuilder(path);
            char a = path.charAt(i);
            char b = path.charAt(i + 1);
            int cost = map[a - 'a'][b - 'a'];
            path.deleteCharAt(i);
            path.deleteCharAt(i);
            tmpCost += cost;
            remove(map);
            tmpCost -= cost;
            path = tmpPath;
        }
    }

}
