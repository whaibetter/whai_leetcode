package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 0:23
 * @注释
 */
public class LeetCode110 {

    public static void main(String[] args) {

        TreeNode treeNode = TreeNode.constructTreeByArray(1, 2, 2, 3, 3, null, null, 4, 4);
//        System.out.println(isBalanced(treeNode));
        System.out.println(isBalanced(TreeNode.constructTreeByArray(1,2,2,3,null,null,3,4,null,null,4)));
    }
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return level(root) != -1;
    }

    public static int level(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = level(root.left);
        if (left==-1) return -1;
        int right = level(root.right);
        if (right==-1) return -1;
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
