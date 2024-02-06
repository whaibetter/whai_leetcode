package cn.whaifree.redo.redo_24_2_4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/4 13:07
 * @注释
 */
public class LeetCode216 {

    @Test
    public void test() {
        for (List<Integer> integers : new Solution().combinationSum3(9, 45)) {
            System.out.println(integers);
        }
    }



    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        /**
         * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
         * @param k
         * @param n
         * @return
         */
        public List<List<Integer>> combinationSum3(int k, int n) {
            recursion(1, 9, k, n);
            return res;
        }

        void recursion(int start, int end, int k, int needNumber) {

            if (needNumber == 0 && path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i <= end; i++) {
                // 标准剪枝
                if (end - i + 1 < k - path.size()) {
                    return;
                }
                path.add(i);
                recursion(i + 1, end, k, needNumber - i);
                path.remove(path.size() - 1);
                // 如果 新加的数i 比 需要的数needNumber大，那么加这些正数是多余的操作
                if (needNumber <= i) {
                    return;
                }
            }
        }
    }
}
