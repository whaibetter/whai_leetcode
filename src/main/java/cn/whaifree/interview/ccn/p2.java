package cn.whaifree.interview.ccn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/8 0:04
 * @注释
 */
public class p2 {

    @Test
    public void test() {
        ArrayList<Integer> ls = new ArrayList<>();
        ls.add(50);
        System.out.println(chooseBestSum(163,3, ls));
    }


    static int now = 0;
    static List<Integer> path = new ArrayList<>();
    static int res = 0;
    /**
     * ls里找到k个数，总和不超过t的最大
     * @param t
     * @param k
     * @param ls
     * @return
     */
    int chooseBestSum(int t, int k, List<Integer> ls) {
        // 在这⾥写代码
        back(t, k, ls, 0);
        return res;
    }

    void back(int t, int k, List<Integer> ls,int start) {
        if (path.size() == k && now <= t) {
            res = Math.max(res, now);
        }
        if (now > t || path.size() > k) {
            return;
        }

        for (int i = start; i < ls.size(); i++) {
            Integer e = ls.get(i);
            now += e;
            path.add(e);
            back(t, k, ls, i + 1);
            path.remove(path.size() - 1);
            now -= e;
        }
    }
}

class c4{


    public static void main(String[] args) {
        System.out.println(formatDuration(62));
    }
    final static int MINUTE = 60;
    final static int HOUR = 60 * MINUTE;
    final static int DAYS = 24  * HOUR;
    final static int YEAR = 365 * DAYS;
    // 本题面试官已设置测试用例
    public static String formatDuration(int seconds) {
        // 在这⾥写代码
        int year = 0;
        int day = 0;
        int hour = 0;
        int minute =0;
        if(seconds>YEAR){
            year = seconds/YEAR;
            seconds%=YEAR;
        }
        if (seconds > HOUR) {
            hour = seconds / HOUR;
            seconds %= HOUR;
        }
        if (seconds > DAYS) {
            day = seconds / DAYS;
            seconds %= DAYS;
        }
        if(seconds>MINUTE){
            minute = seconds/MINUTE;
            seconds%=MINUTE;
        }
        if (seconds != 0) {

        }


        /**
         * - 微服务限流方案
         *
         * 架构图
         * 指标选择、计算、指标精确性
         * 限流策略
         * 具体实现
         * - 限流
         * - 拒绝服务实现
         */

        return "";
    }

}
