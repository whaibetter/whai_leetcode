package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 15:51
 * @注释
 */
public class LeetCode216
{


    @Test
    public void test()
    {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum3(2, 18);
        System.out.println(lists);
    }


    class Solution {

        List<List<Integer>> res;
        List<Integer> path;
        int sum = 0;
        public List<List<Integer>> combinationSum3(int k, int n) {
            res = new ArrayList<>();
            path = new ArrayList<>();
            backTracking(k, n, 1);
            return res;
        }

        public void backTracking(int k, int n, int startIndex)
        {
            if (k == path.size() && n == sum) {
                res.add(new ArrayList<>(path));
            }

            for (int i = startIndex; i < 10; i++) {
                if (sum + i > n) {
                    break;
                }
                sum += i;
                path.add(i);
                backTracking(k, n, i + 1);
                sum -= i;
                path.remove(path.size() - 1);
            }
        }
    }
}
