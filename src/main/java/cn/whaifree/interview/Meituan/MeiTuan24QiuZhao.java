package cn.whaifree.interview.Meituan;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
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

    @Test
    public void test1() {
        Solution_2 solution_2 = new Solution_2();
        solution_2.max(new int[]{1,4,4});
    }

    class Solution_2{

        public void max(int[] ints) {
            // 统计每个数出现的数量
            // 遍历每个数i，判断i+1和i-1的数量是否>0,如果>0,就进行一次操作 i+1和i-1的数量-1，i的数量+2
            // 每经过一次操作，需要重新遍历
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int anInt : ints) {
                Integer integer = map.get(anInt);
                if (integer == null) {
                    map.put(anInt, 1);
                }else {
                    map.put(anInt, integer + 1);
                }
            }

            map.forEach(
                    (k, v) -> {
                        System.out.println(k + ":" + v);
                    }
            );



        }

    }

    @Test
    public void test2() {
        Solution_3 solution_3 = new Solution_3();
        int[] ints = {1,0,0,0,1};
        solution_3.max(ints);
    }

    class Solution_3{

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            char[] chars = in.nextLine().toCharArray();
            // chars转为 ints
            int[] ints = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                ints[i] = chars[i] - '0';
            }
            max(ints);
        }

        public static void max(int[] ints) {

            int res = 0;
            for (int i = 0; i < ints.length; i++) {
                for (int j = 1; i + j < ints.length; j++) {
                    res += change(Arrays.copyOf(ints, ints.length), i, i + j);
                }
            }
            System.out.println(res);
        }

        public static int change(int[] ints, int start, int end) {
            int res = 0;
            while (start < end ) {
                if (ints[start] == ints[start + 1]) {

                    res++;
                    ints[start + 1] = ints[start + 1] == 1 ? 0 : 1;
                }
                start++;
            }
            return res;
        }

    }
}
