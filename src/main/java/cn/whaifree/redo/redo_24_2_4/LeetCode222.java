package cn.whaifree.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/3 20:04
 * @注释
 */
public class LeetCode222 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5, 6});
        treeNode.printTree();
        System.out.println(new Solution().countNodes(treeNode));
    }

   class Solution{
       /**
        * 完全二叉树二分查找
        * @param root
        * @return
        */
       public int countNodes(TreeNode root) {
           if (root==null) return 0;

           TreeNode left = root.left;
           int leftDepth = 0;
           while (left != null) {
               leftDepth++;
               left = left.left;
           }

           TreeNode right = root.right;
           int rightDepth = 0;
           while (right != null) {
               rightDepth++;
               right = right.right;
           }

           if (leftDepth == rightDepth) {
               return (2 << leftDepth) - 1;
           }
           return countNodes(root.left) + countNodes(root.right) + 1;
       }
   }


}
