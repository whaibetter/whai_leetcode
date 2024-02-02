package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 20:24
 * @注释
 */
public class LeetCode77 {

    @Test
    public void test() {
        new Solution().combine(4, 4).forEach(
                list -> {
                    list.forEach(System.out::print);
                    System.out.println();
                }
        );
    }


    class Solution {
        List<List<Integer>> res = new LinkedList<>();

        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            circle(1, n, k);
            return res;
        }

        void circle(int start, int end, int k) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 剪枝，可以选择的个数不满足最小需要的个数
            // 已经选择的个数 path.size
            // 还需要的个数 k-path.size
            // 可以选择的个数<还需要的个数 end-i<k-path.size 时 退出
            // 即 end-i>k-path.size时正常执行 i<end-(k-path.size) 时正常执行
            for (int i = start; i <= end - (k - path.size()) + 1; i++) {
                path.add(i);
                circle(i + 1, end, k);
                path.remove(path.size() - 1);
            }
        }

    }
}
