package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 13:32
 * @注释
 */
public class LeetCode105 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = solution.buildTree(preorder, inorder);
        treeNode.printTree();
    }

    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        /**
         * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         */
        public TreeNode build(
                int[] preorder,
                int[] inorder,
                int preStart,
                int preEnd,
                int inStart,
                int inEnd
        ) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            int head = preorder[preStart];
            TreeNode h = new TreeNode(head);

            int inS = 0; // 标记中序中在哪
            while (inorder[inS] != head) {
                inS++;
            }
            int l = inS - inStart; // 左边有几个元素
            h.left = build(preorder, inorder, preStart + 1, preStart + l, inStart, inS - 1);
            h.right = build(preorder, inorder, preStart + l + 1, preEnd, inS + 1, inEnd);
            return h;
        }
    }

}
