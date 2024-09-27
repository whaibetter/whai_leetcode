package cn.whaifree.interview.HS.Qz;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 18:56
 * @注释
 */
public class P1 {

    public static void main(String[] args) {
        // a + b = c 一只c 求 a*b 的最大值
        Scanner scanner = new Scanner(System.in);
        long i = scanner.nextLong();
        long maxProduct = 0;
        for (int j = 0; j <= i; j++) {
            long b = i - j;
            long product = b * j;
            maxProduct = Math.max(product, maxProduct);
        }
        System.out.println(maxProduct);
    }
}

class p2{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int k = in.nextInt();
        if (n <= 1) {
            System.out.println(-1);
            return;
        }
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }

        int tmp = 0;
        for (int i = 1; i < nums.length; i++) {
            int search = search(nums[i], nums[i - 1]);
            if (search != -1) {
                tmp = Math.max(search, tmp);
            }
        }
        System.out.println(tmp);

    }

    public static int search(int a, int b) {
        int tmp = Math.min(a, b);
        while (tmp > 0) {
            if (a % tmp == 0 && b % tmp == 0) {
                return tmp;
            }
            tmp--;
        }
        return -1;
    }
}
