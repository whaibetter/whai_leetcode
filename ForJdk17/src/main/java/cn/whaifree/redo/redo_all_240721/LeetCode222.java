package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 19:40
 * @注释
 */
public class LeetCode222 {
    public static void main(String[] args) {
        System.out.println(countNodes(TreeNode.constructTreeByArray(1, 2, 3, 4, 5, 6)));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root);
    }

    public static int count(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode i = root;
        int leftDepth = 0;
        while (i.left != null) {
            i = i.left;
            leftDepth++;
        }
        i = root;
        int rightDepth = 0;
        while (i.right != null) {
            i = i.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1;
        }else {
            return count(root.left) + count(root.right) + 1;
        }
    }
}
