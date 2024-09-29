package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 22:36
 * @注释
 */
public class LeetCode450 {

    public static void main(String[] args) {
//        deleteNode(TreeNode.constructTreeByArray(5, 3, 6, 2, 4, null, 7), 3).printTree();
        deleteNode(TreeNode.constructTreeByArray(50,30,70,null,40,60,80), 50).printTree();
    }
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            TreeNode tmp = root.right;
            if (tmp == null) {
                return root.left;
            }
            TreeNode tmpLeft = root.left;
            TreeNode index = tmp;
            while (index.left != null) {
                index = index.left;
            }
            index.left = tmpLeft;
            root = tmp;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

}
