package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/29 21:43
 * @注释
 */
public class LeetCode701 {


    public static void main(String[] args) {

        new LeetCode701().insertIntoBST(TreeNode.constructTreeByArray(4, 2, 7, 1, 3), 5).printTree();
        new LeetCode701().insertIntoBST(TreeNode.constructTreeByArray(40,20,60,10,30,50,70), 25).printTree();


    }
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

}
