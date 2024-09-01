package cn.whaifree.interview.Meituan;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 21:10
 * @注释
 */
public class TestDemo {

    @org.junit.Test
    public void test() {
        int[] nums = {999999993,999999993,999999993,999999993,999999993};
        int k = 999999993;
        System.out.println(Test.find1(nums, k));

    }


    /**
     * <a href="https://www.nowcoder.com/exam/test/82794641/detail?pid=52205528&examPageSource=Company&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D2024%26selectStatus%3D0%26tagIds%3D179&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91">...</a>
     */
    static class Test {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int num = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[num];
            for (int i = 0; i < num; i++) {
                nums[i] = in.nextInt();
            }
            System.out.println(find(nums, k));
        }


        /**
         * 最长字数组，满足平均==k
         *
         * @param nums
         * @param k
         */
        public static int find(int[] nums, int k) {
            int left = 0;
            int right = 0;
            long nowSum = 0;
            int maxLen = Integer.MIN_VALUE;
            while (right < nums.length) {
                nowSum += nums[right];
                while (left <= right && nowSum > (long) k * (right - left + 1)) {
                    nowSum -= nums[left];
                    left++;
                }
                if ((long) k * (right - left + 1) == nowSum) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
                right++;
            }

            return maxLen == Integer.MIN_VALUE ? -1 : maxLen;
        }

        /**
         * 最长字数组，满足平均==k
         *
         * @param nums
         * @param k
         */
        public static int find1(int[] nums, int k) {


            int maxLen = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                long nowSum = 0;
                for (int j = i; j < nums.length; j++) {
                    nowSum += nums[j];
                    if (nowSum == (long) k * (j - i + 1)) {
                        maxLen = Math.max(maxLen, j - i + 1);
                    }
                }
            }
            return maxLen == Integer.MIN_VALUE ? -1 : maxLen;
        }


    }
}

