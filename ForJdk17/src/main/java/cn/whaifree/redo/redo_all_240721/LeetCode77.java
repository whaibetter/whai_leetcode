package cn.whaifree.redo.redo_all_240721;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 20:26
 * @注释
 */
public class LeetCode77
{

    public static void main(String[] args)
    {
        int n = 4;
        int k = 2;
        System.out.println(new LeetCode77().combine(n, k));
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public  List<List<Integer>> combine(int n, int k)
    {
        circle(n, 1, k);
        return res;
    }

    public void circle(int n, int start, int k) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        /**
         * n start k i
         * 能够提供的数 n-i
         * 还需要的数 k - path.size
         */
        for (int i = start; i <= n; i++) {
            // 剪枝
            if (n - i < k - path.size() - 1) {
                break;
            }
            path.add(i);
            circle(n, i + 1, k);
            path.remove(path.size() - 1);
        }
    }
}
