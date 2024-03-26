package cn.whaifree.interview.Meituan;

import org.junit.Test;

import java.util.Scanner;

/**
 * 【刷题节】美团2024届秋招笔试第二场编程真题
 * <a href="https://www.nowcoder.com/exam/test/78800845/detail?pid=52007812&examPageSource=Company&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E7%B1%B3%E5%93%88%E6%B8%B8%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91">...</a>
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/25 15:04
 * @注释
 */
public class MeiTuan24QiuZhao {

    @Test
    public void test() {
        Solution_1 solution_1 = new Solution_1();
        //1, 1 4 5 1 4
        solution_1.getMax(new int[]{1, 1, 4, 5, 1, 4});
    }


    class Solution_1{

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            getMax(nums);
        }

        public static void getMax(int[] nums) {

            long sum = 0;
            for (int num : nums) {
                sum += num;
            }

            long max = sum;
            for (int i = 0; i < nums.length - 1; i++) {
                max = Math.max(max, (sum - nums[i] - nums[i + 1]) + ((long) nums[i] * nums[i + 1]));
            }
            System.out.println(max);

        }

    }
}
