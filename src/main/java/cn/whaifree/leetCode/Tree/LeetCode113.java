package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/24 21:30
 * @注释
 */
public class LeetCode113 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{5,4,8,null,4});
        treeNode.printTree();
        System.out.println(new Solution().pathSum(treeNode, 13));
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return res;
            }
            level(root, targetSum, new ArrayList<>());
            return res;
        }


        /**
         *
         * @param root 节点
         * @param needSum 还需要的数
         * @param nowHasPath 记录已经走过的路，也用于回退
         */
        public void level(TreeNode root, int needSum,ArrayList<Integer> nowHasPath) {
            if (root == null) {
                return;
            }
            nowHasPath.add(root.val);
            if (root.left == null && root.right == null && root.val == needSum) {
                res.add(new ArrayList<>(nowHasPath));
                // 回退全部交给上一次递归
            }
            if (root.left != null) {
                level(root.left, needSum - root.val, nowHasPath);
                nowHasPath.remove(nowHasPath.size() - 1);
            }
            if (root.right != null) {
                level(root.right, needSum - root.val, nowHasPath);
                nowHasPath.remove(nowHasPath.size() - 1);
            }

        }
    }
}
