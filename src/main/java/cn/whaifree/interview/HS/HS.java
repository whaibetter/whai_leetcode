package cn.whaifree.interview.HS;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/11 17:54
 * @注释
 */
public class HS {

    public static void main1(String[] args) {

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        // 大于10 小于该n

        for (int i = n; i > 10; i--) {
            get(i);
        }
    }

    /**
     * 列举出小于小于max，且长度为n的所有满足的数
     * @param n
     */
    public static void get(int n) {
        // 获取n的长度
        int length = 0;
        int newN = n;
        while (newN != 0) {
            length++;
            newN /= 10;
        }
        newN = n;
        int res = 0;
        while (newN > 0) {
            int c = newN % 10;
            newN /= 10;
            res += Math.pow(c, length);
        }
        if (res == n) {
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        double[] doubles = {3, 2, 6, 5, 1, 3};
        System.out.println(get_max_profit(10000, 6, doubles, 2));

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 根据输入计算最大收益
     * @param M double浮点型 初始资金
     * @param N int整型 历史价格天数
     * @param historyPrices double浮点型一维数组 N天历史价格
     * @param K int整型 最大允许交易次数
     * @return double浮点型
     */
    public static double get_max_profit (double M, int N, double[] historyPrices, int K) {
        // write code here
        // dp[0] 表示当天手里没有股票的手里的钱
        //      - 没有钱有两种
        // dp[1] 表示手里有股票手里的钱
        double[] buy = new double[K + 1];
        double[] cell = new double[K + 1];
        buy[0] = -historyPrices[0];
        cell[0] = 0;
        for (int i = 1; i <= K; i++) {
            buy[i] = cell[i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < N; i++) {
            buy[0] = Math.max(buy[0], cell[0] - historyPrices[i]);
            for (int j = 1; j <= K; j++) {
                buy[j] = Math.max(buy[j], cell[j] - historyPrices[i]);
                cell[j] = Math.max(cell[j], buy[j - 1] + historyPrices[i]);
            }
        }

        // 不持有股票的最大利润 cell 累乘后扣除初始值
        double res = 1;
        for (double v : cell) {
            if (v != 0) {
                res *= v;
            }
        }
        res *= M;
        res -= M;
        return res;
    }

}
