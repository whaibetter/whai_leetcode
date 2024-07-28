package cn.whaifree.redo.redo_all_240721;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 21:46
 * @注释
 */
public class LeetCode216 {
    public static void main(String[] args) {
        System.out.println(combinationSum3(9, 45));
    }
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static int nowSum = 0;
    public static List<List<Integer>> combinationSum3(int k, int n) {
        circle(k, n, 1);
        return res;
    }

    public static void circle(int k, int n, int start) {
        if (nowSum == n && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= 9; i++) {
            /**
             * nowSum + i > n
             * path.size+9-i>k
             */
            if (nowSum + i > n) {
                break;
            }
            if ((9 - i + 1) < k - path.size()) {
                break;
            }
            nowSum += i;
            path.add(i);
            circle(k, n, i + 1);
            path.remove(path.size() - 1);
            nowSum -= i;
        }
    }
}
