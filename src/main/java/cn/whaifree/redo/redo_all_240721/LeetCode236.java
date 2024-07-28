package cn.whaifree.redo.redo_all_240721;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 20:43
 * @注释
 */

import cn.whaifree.leetCode.model.TreeNode;

public class LeetCode236 {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.constructTreeByArray(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        lowestCommonAncestor(treeNode, new TreeNode(6), new TreeNode(4)).printTree();
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (right == null && left == null) {
            return null;
        }
        if (right != null && left != null) {
            return root;
        } if (right != null) {
            return right;
        } else {
            return left;
        }
    }
}
