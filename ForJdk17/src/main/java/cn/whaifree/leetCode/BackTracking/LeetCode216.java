package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/2 8:22
 * @注释
 */
public class LeetCode216 {

    @Test
    public void test() {
        Solution solution = new Solution();
        solution.combinationSum3(9, 45).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {

        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        int sum = 0;
        /**
         * 相加之和为n的k个数的组合
         * @param k
         * @param n
         * @return
         */
        public List<List<Integer>> combinationSum3(int k, int n) {
            circle(1, 9, n, k);
            return res;
        }

        public void circle(int start, int end, int n, int k) {
            if (path.size() == k && sum == n) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 1. 如果sum>n了，证明往后加只会更大，因为都是正数，就不再继续了
            // sum>n
            //
            // 2. 如果 9个数要9个数
            // 已经选择size
            // 还需选择k-size
            // 可以选择的数end-start
            // 可以选择的数<还需选择的数 end-start<k-size

            for (int i = start; i <= end ; i++) {
                // 可以选择的数<还需选择的数
                if (end - i + 1 < k - path.size()) {
                    return;
                }
                path.add(i);
                sum += i;

                // 如果 加上某个数已经超过了n，那么剩下的正数都没必要再加了。如1234，后面只能选5678，如果去了4加了5678必然会超过n
                if (sum > n) {
                    path.remove(path.size() - 1);
                    sum -= i;
                    return;
                }
                circle(i + 1, end, n, k);
                path.remove(path.size() - 1);
                sum -= i;
            }
        }
    }



}
