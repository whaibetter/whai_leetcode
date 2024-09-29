package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 0:16
 * @注释
 */
public class LeetCode101 {

    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println(isSymmetric(TreeNode.constructTree(new Integer[]{1, 2, 2, 3, 1, 4, 3})));
    }
    public static boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private static boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean one = isSymmetric(p.right, q.left);
        boolean two = isSymmetric(p.left, q.right);
        return one && two;
    }
}
