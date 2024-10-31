package cn.whaifree.interview.PA;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 17:38
 * @注释
 */
public class P1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int m = in.nextInt();

            /**
             *   0 1 2
             * 0 0 0 0
             * 1 1 1 1
             * 2 1 2 3
             * 3 1 3 6
             */
            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
            System.out.println(dp[m]);


        }
    }



}

class p2{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        int res = 0;
        for (int right = 0; right <= str.length(); right++) {
            for (int left = 0; left < right; left++) {
                String leftStr = str.substring(0, left);
                String rightStr = str.substring(right, str.length());
                String concat = leftStr + rightStr;
                if (!concat.isEmpty() && huiwen(concat)) {
                    System.out.println(concat);
                    res++;
                }
            }
        }

        System.out.println(res);

    }

    public static boolean huiwen(String str){
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
