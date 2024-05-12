package cn.whaifree.redo.redo.redo_24_3_9;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 19:17
 * @注释
 */
public class LeetCode450 {

    @Test
    public void test() {
        new Solution().deleteNode(TreeNode.constructTreeByArray(), 5).printTree();
    }

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            return circle(root, key);
        }

        public TreeNode circle(TreeNode root,int key) {
            if (root == null) {
                return null;
            }

            if (root.val == key) {
                // 将root.left全部移动到root.right.left.left....
                TreeNode index = root.right;
                // 如果右边没有节点，直接返回左边的
                if (index == null) {
                    return root.left;
                }
                while (index.left != null) {
                    index = index.left;
                }
                index.left = root.left;
                return root.right;
            }

            if (root.val < key) {
                root.right = circle(root.right, key);
            }else {
                root.left = circle(root.left, key);
            }
            return root;
        }
    }
}
