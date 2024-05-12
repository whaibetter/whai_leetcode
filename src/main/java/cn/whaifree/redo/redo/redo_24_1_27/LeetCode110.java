package cn.whaifree.redo.redo.redo_24_1_27;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/27 17:21
 * @注释
 */
public class LeetCode110 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1,2,2,3,null,null,3,4,null,null,4});

        System.out.println(new Solution().isBalanced(treeNode));
    }


    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return level(root) != -1;
        }

        public int level(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 不要在这里+1，容易混淆 -1（如果子树不平衡）+1=0，在最下面返回值里层数+1
            int right = level(root.right);
            int left = level(root.left);
            // 一旦有不平衡，要立刻返回
            if (right==-1||left==-1) return -1;

            if (Math.abs(right - left) > 1) {
                return -1;
            }
            return Math.max(right, left) + 1;
        }
    }

}
