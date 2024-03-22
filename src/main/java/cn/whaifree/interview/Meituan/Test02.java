package cn.whaifree.interview.Meituan;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * <a href="https://www.nowcoder.com/discuss/599738882497736704?sourceSSR=search">...</a>
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 19:14
 * @注释
 */
public class Test02 {
    public static void main(String[] args) {
        new Test02().test();
    }


    public void test() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        for (int i = 0; i < number; i++) {
            int num = scanner.nextInt();
            int[] input = new int[num];
            for (int j = 0; j < num; j++) {
                input[j] = scanner.nextInt();
            }

            int[] output = new int[num];
            for (int j = 0; j < num; j++) {
                output[j] = scanner.nextInt();
            }

            isSuccess(input, output);
        }

    }

    public void isSuccess(int[] input, int[] output) {
        LinkedList<Integer> stack = new LinkedList<>();
        int index = 0;
        for (int i : input) {
            stack.push(i);
            while (!stack.isEmpty() && stack.peek() == output[index++]) {
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Test
    public void test1() {
        int[] input = {1, 2, 3, 4, 5};
        int[] output = {4, 5, 3, 2, 1};
        System.out.println(new Solution946().validateStackSequences(input, output));
    }

    class Solution946 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            return success(pushed, popped);
        }

        public boolean success(int[] input, int[] output) {
            LinkedList<Integer> stack = new LinkedList<>();
            int index = 0;
            for (int i : input) {
                stack.push(i);
                while (!stack.isEmpty() && stack.peek() == output[index]) {
                    index++;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }

    @Test
    public void test2() {
        sugar(new int[]{3, 1, 2, 7, 10, 2, 4});
    }


    /**
     * @param values
     */
    public void sugar(int[] values) {
        // dp[i]表示当前最大价值
        // dp[i]=max(dp[i-1],dp[i-3]+values[i])
        /**
         *
         * 3 1 2 7 10 2 4
         *
         * 3 3 3 10 13 13 14
         *
         */

        // 初始化前3个
        int[] dp = new int[values.length];
        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, values[i]);
        }
        for (int i = 0; i < 3; i++) {
            dp[i] = max;
        }

        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 3] + values[i]);
        }

        System.out.println(Arrays.toString(dp));

    }

    /**
     * dp[i]表示从商品0-i中的最美味值
     * <p>
     * 获取最大的k个值，遇到最大的这k个值就必吃
     * <p>
     * dp[i] =
     * 1. 如果values[i]不存在于k大个值，如果这个不吃 dp[i-1]
     * 2. 如果吃      dp[i-2] + values[i]
     * 3. 如果存在于最大的k个值 dp[i-1] + values[i]
     * <p>
     * 1 2 3 4 5 6 7
     * <p>
     * 0 1 2 3 4 5 6
     * 1 2 4 6 9 12 16
     *
     * @param values 美味值
     * @param k      允许打破的次数
     */
    public int calculateMaxTaste(int[] values, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int value : values) {
            priorityQueue.add(value);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(priorityQueue.poll());
        }

        int[] dp = new int[values.length];
        dp[0] = values[0];
        dp[1] = Math.max(values[0], values[1]);
        for (int i = 2; i < dp.length; i++) {
            if (list.contains(values[i])) {
                System.out.println(i + "打破规则:" + values[i]);
                dp[i] = dp[i - 1] + values[i]; //打破规则
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + values[i]);
            }
        }

        return dp[values.length - 1];
    }

    @Test
    public void test3() {
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        int k = 1;
//        sugar(values, k);


    }


    @Test
    public void testCalculateMaxTaste() {

        Test02 strategy = new Test02();
        // 测试用例1
        int n1 = 3, k1 = 1;
        int[] delicious1 = {1, 2, 3};
        Assert.assertEquals(5, strategy.calculateMaxTaste(delicious1, k1));

        // 测试用例2
        int n2 = 4, k2 = 2;
        int[] delicious2 = {1, 2, 3, 4};
        Assert.assertEquals(9, strategy.calculateMaxTaste(delicious2, k2));

        // 测试用例3
        int n3 = 5, k3 = 1;
        int[] delicious3 = {1, 3, 2, 4, 5};
        Assert.assertEquals(12, strategy.calculateMaxTaste(delicious3, k3));

        // 测试用例4
        int n4 = 3, k4 = 0;
        int[] delicious4 = {3, 1, 2};
        Assert.assertEquals(3, strategy.calculateMaxTaste(delicious4, k4));

        // 测试用例5
        int n5 = 4, k5 = 1;
        int[] delicious5 = {1, 1, 1, 1};
        Assert.assertEquals(3, strategy.calculateMaxTaste(delicious5, k5));

        // 测试用例6
        int n6 = 6, k6 = 2;
        int[] delicious6 = {5, 2, 4, 1, 3, 6};
        Assert.assertEquals(16, strategy.calculateMaxTaste(delicious6, k6));

        // 测试用例7
        int n7 = 5, k7 = 2;
        int[] delicious7 = {2, 3, 1, 5, 4};
        Assert.assertEquals(11, strategy.calculateMaxTaste(delicious7, k7));

        // 测试用例8
        int n8 = 4, k8 = 0;
        int[] delicious8 = {4, 3, 2, 1};
        Assert.assertEquals(4, strategy.calculateMaxTaste(delicious8, k8));

        // 测试用例9
        int n9 = 6, k9 = 3;
        int[] delicious9 = {2, 1, 5, 3, 6, 4};
        Assert.assertEquals(19, strategy.calculateMaxTaste(delicious9, k9));

        // 测试用例10
        int n10 = 7, k10 = 1;
        int[] delicious10 = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(26, strategy.calculateMaxTaste(delicious10, k10));
    }


}
