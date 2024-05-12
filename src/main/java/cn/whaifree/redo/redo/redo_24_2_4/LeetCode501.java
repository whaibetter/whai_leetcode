package cn.whaifree.redo.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/3 23:28
 * @注释
 */
public class LeetCode501 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(6,2,8,0,4,7,9,null,null,2,6);
        for (int i : new Solution().findMode(root)) {
            System.out.println(i);
        }
    }


    class Solution {


        TreeNode pre = null;
        int most = 0;
        int markCount = 0;
        List<Integer> res = new ArrayList<>();
        public int[] findMode(TreeNode root) {
            recursion(root);
            int[] re = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                re[i] = res.get(i);
            }
            return re;
        }

        public void recursion(TreeNode root) {
            if (root == null) {
                return;
            }
            recursion(root.left);


            if (pre == null) {
                // 第一个节点走
                markCount = 1;
            } else if (pre.val == root.val) {
                markCount++;
            } else {
                markCount = 1;
            }

            if (markCount > most) {
                res.clear();
                most = markCount;
                res.add(root.val);
            } else if (markCount == most) {
                res.add(root.val);
            }

            pre = root;
            recursion(root.right);
        }
    }


}
