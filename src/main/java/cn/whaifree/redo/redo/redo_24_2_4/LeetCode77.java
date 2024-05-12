package cn.whaifree.redo.redo.redo_24_2_4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/4 12:31
 * @注释
 */
public class LeetCode77 {

    @Test
    public void test(
    ) {
        for (List<Integer> integers : new Solution().combine(5, 2)) {
            System.out.println(integers);
        }
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        /**
         * 返回范围 [1, n] 中所有可能的 k 个数的组合。
         * @param n
         * @param k
         * @return
         */
        public List<List<Integer>> combine(int n, int k) {
            recursion(1, n, k);
            return res;
        }

        /**
         * 从start 到 end 里选择一个数，放入path
         * @param start 开始的数
         * @param end 结束的数
         * @param k
         */
        void recursion(int start, int end, int k) {



            if (path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i <= end; i++) {

                // 剪枝
                // 已经选择的数字size
                // 还需选择的数k-size
                // 可以选择的数 end-start
                // 如果end-start<k-size 直接return
                if (end - i + 1 < k - path.size()) {
                    return;
                }

                path.add(i);
                recursion(i + 1, end, k);
                path.remove(path.size() - 1);
            }
        }
    }
}
