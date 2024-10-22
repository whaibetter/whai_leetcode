package cn.whaifree.interview.jr;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 12:11
 * @注释
 */
public class p1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int people = in.nextInt();
        int gzNum = in.nextInt();
        int[] nums = new int[people];
        for (int i = 0; i < people; i++) {
            nums[i] = in.nextInt();
        }

        int[] gzNums = new int[gzNum];

        for (int i = 0; i < nums.length; i++) {
            int want = nums[i];
            gzNums[want - 1]++;
        }

        int res = 0;
        for (int num : gzNums) {
            int needSum = num / 2;
            if (num % 2 == 1) {
                res += (needSum + 1);
            } else {
                res += (needSum);
            }
        }
        System.out.println(res);
    }
}


class p2{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int v0 = in.nextInt(); // 初始速度
        int x = in.nextInt(); // v=v0+t*x
        int y = in.nextInt(); // 总里程
        // t1 = y / v = y / (v0+t*x)
        // t = 2 / (t) t

        // 速度t 2   2/t=2.8284271  t = 2/更好8 = 根号2/2
        double t = Math.sqrt(2) / 2;
        System.out.println(y / (v0 + t * x));

    }
}
