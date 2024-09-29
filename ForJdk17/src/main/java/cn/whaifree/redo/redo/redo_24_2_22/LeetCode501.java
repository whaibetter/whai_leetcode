package cn.whaifree.redo.redo.redo_24_2_22;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 12:04
 * @注释
 */
public class LeetCode501 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(1,null,2);
        for (int i : new Solution().findMode(root)) {
            System.out.println(i);
        }
    }


    class Solution {
        List<Integer> res = new ArrayList<>();
        // 最高出现的次数
        int most = 0;
        // 当前临时记录的值
        int tmpValue = Integer.MIN_VALUE;
        // 临时记录的值得次数
        int tmpCount = 0;

        public int[] findMode(TreeNode root) {
            recursion(root);
            int[] ints = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                ints[i] = res.get(i);
            }
            return ints;
        }

        public void recursion(TreeNode root) {
            if (root == null) {
                return;
            }
            recursion(root.left);
            if (root.val == tmpValue) {
                tmpCount++;
            }else{
                tmpValue = root.val;
                tmpCount = 1;
            }

            if (tmpCount > most) {
                most = tmpCount;
                res.clear();
                res.add(root.val);
            } else if (tmpCount == most) {
                res.add(root.val);
            }


            recursion(root.right);
        }
    }
}
