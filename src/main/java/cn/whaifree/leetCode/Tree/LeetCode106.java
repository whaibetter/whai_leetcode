package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/25 21:29
 * @注释
 */
public class LeetCode106 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});
        // 9 3 20 7 15
        // 20 3 7 9 15
        // 20 7 3 15 9
        new Solution().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}).printTree();

    }

    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0) {
                return null;
            }
            return circle(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);

        }
        public TreeNode circle(
                int[] inorder,
                int[] postorder,
                int inorderStart,
                int inorderEnd,
                int poster
        ) {
            if (poster < 0 || inorderStart > inorderEnd) {
                return null;
            }
            int lastValue = postorder[poster];
            // 中序的指针，左边为左树，右边为右树。
            int index = 0;
            while (inorder[index] != lastValue) {
                index++;
            }
            TreeNode root = new TreeNode(lastValue);
            root.right = circle(inorder, postorder, index + 1, inorderEnd, index+inorderEnd-2);
            root.left = circle(inorder, postorder, inorderStart, index - 1,inorderStart+index-1 );
            return root;
        }
    }

}
