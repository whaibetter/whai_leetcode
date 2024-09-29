package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 15:13
 * @注释
 */
public class LeetCode77 {

    @Test
     public void test()
     {
         Solution solution = new Solution();
         List<List<Integer>> lists = solution.combine(4, 2);
         System.out.println(lists);
     }

    class Solution {

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> combine(int n, int k) {
            backtrack(n, k, 1);
            return res;
        }

        public void backtrack(int n, int k, int startIndex)
        {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
                return;
            }
//            if (startIndex > n) {
//                return;
//            }

            for (int i = startIndex; i <= n; i++) {
                // 剪枝
                if (n - startIndex + 1 < k - path.size()) {
                    return;
                }
                path.add(i);
                backtrack(n, k, i + 1);
                path.remove(path.size() - 1);
            }



        }
    }
}
