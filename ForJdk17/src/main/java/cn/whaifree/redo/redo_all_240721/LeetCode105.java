package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 18:31
 * @注释
 */
public class LeetCode105 {

    public static void main(String[] args) {
//        int[] preorder = {3, 9, 20, 15, 7};
//        int[] inorder = {9, 3, 15, 20, 7};
//        TreeNode root = buildTree(preorder, inorder);
//        root.printTree();
//
//        TreeNode treeNode = buildTree(new int[]{1}, new int[]{1});
//        TreeNode.printTree(treeNode);

        buildTree(new int[]{1,2,3}, new int[]{3,2,1}).printTree();
    }

    /**
     *
     *
     * @param preorder {3, 9, 20, 15, 7}
     * @param inorder {9, 3, 15, 20, 7}
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        if (inStart == inEnd) {
            return new TreeNode(inorder[inStart]);
        }

        // 前序中的第一个
        int want = preorder[preStart];
        // 在中序中找到分界的位置
        int inorderIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == want) {
                inorderIndex = i;
                break;
            }
        }
        // 这个节点中序的左边有几个节点
        int leftInorderLen = inorderIndex - inStart;
        TreeNode node = new TreeNode(want);
        node.left = build(preorder, inorder, preStart + 1, preStart + leftInorderLen, inStart, inorderIndex - 1);
        node.right = build(preorder, inorder, preStart + leftInorderLen + 1, preEnd, inorderIndex + 1, inEnd);
        return node;
    }
}
