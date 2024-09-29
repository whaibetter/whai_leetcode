package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 14:57
 * @注释
 */
public class LeetCode222 {
    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(1, 2, 3, 4, 5, 6);
        System.out.println(new Solution().countNodes(root));
    }

    class Solution {
        /**
         * 1. 左右都没有
         * 2. 左有右没有
         * @param root
         * @return
         */
        public int countNodes(TreeNode root) {

            // 左边深度
            // 右边深度
            // 如果左右边深度不一样，往右边下去找
            // 如果左右深度一样，直接返回 2<<level - 1

            return cir(root);
        }
        public int cir(TreeNode root) {
            if (root == null) {
                return 0;
            }
            TreeNode leftIndex = root;
            int leftLevel = 0;
            while (leftIndex != null) {
                leftIndex = leftIndex.left;
                leftLevel++;
            }
            TreeNode rightIndex = root;
            int rightLevel = 0;
            while (rightIndex != null) {
                rightIndex = rightIndex.right;
                rightLevel++;
            }
            if (leftLevel != rightLevel) {
                return cir(root.right) + cir(root.left) + 1;
            } else {
                return (2 << (leftLevel - 1)) - 1;
            }
        }
    }
}
