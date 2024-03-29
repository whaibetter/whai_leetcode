package cn.whaifree.interview.XieChen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/28 19:15
 * @注释
 */
public class xc328 {

    static class p1{

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    stringBuilder.append("you");
                }else {
                    stringBuilder.append("uoy");
                }
            }
            System.out.println(stringBuilder.toString());

        }
    }

    static class p2{
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] ints = new int[n][m];
            for (int i = 0; i < n; i++) {
                String[] split = in.next().split("");
                for (int j = 0; j < m; j++) {
                    ints[i][j] = Integer.parseInt(split[j]);
                }
            }

            // 00 变11
            // 11 不变
            // 10 向下走1
            // 01 变11
            int res = 0;
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < m - 1; j++) {
                    int i1 = ints[i][j];
                    int i2 = ints[i][j + 1];
                    if (i1 == 0 && i2 == 0) {
                        ints[i][j] = 1;
                        ints[i][j + 1] = 1;
                        res++;
                    } else if (i1 == 0 && i2 == 1) {
                        ints[i][j] = 1;
                        res++;
                    } else if (i1 == 1 && i2 == 0 && j == m - 2) {
                        ints[i][j] = 1;
                        res++;
                    }
                }
            }
            System.out.println(res);
        }
    }



    static class p3{

        public static void t(int[] nums) {


        }

        // 将偶数的区间/2 让整个区间最大
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextLong();
            }
            // 获取前缀和
            long[] preSum = new long[n + 1];
            for (int i = 1; i < n + 1; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }


            long nowSum = 0;
            long minSum = Integer.MAX_VALUE;
            // 滑动窗口，让窗口内的值都为偶数，并且是总和最小的窗口

            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    if (Math.abs(nums[j] % 2) == 1) {
                        break;
                    }
                    nowSum += nums[j];
                    minSum = Math.min(nowSum, minSum);
                }
                nowSum = 0;
            }

//            while (right < n) {
//                if (Math.abs(nums[right] % 2) == 1) {
//                    right++;
//                    left = right;
//                    nowSum = 0;
//                    continue;
//                }
//                nowSum += nums[right];
//                while (nowSum > minSum && left < right) {
//                    nowSum -= nums[left];
//                    left++;
//                }
//                minSum = Math.min(nowSum, minSum);
//                right++;
//            }

            long sum = 0;
            for (long num : nums) {
                sum += num;
            }
            System.out.println(sum - minSum / 2);
        }
    }

    static class p4{

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < n; i++) {
                ints[i] = i;
            }

            Arrays.sort(ints);
            // 1 1*2 1*2*3 1*2*3*4


            for (int i = 0; i < ints.length; i++) {
                pro(ints[i],i);
            }

        }

        // 4 6 8
        public static long pro(long n, long start) {
            int res = 1;
            for (long i = start; i < n; i++) {
                res *= i;
            }
            return res;
        }
    }
}
