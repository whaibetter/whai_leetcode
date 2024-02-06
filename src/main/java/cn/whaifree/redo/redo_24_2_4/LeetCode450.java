package cn.whaifree.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/4 0:26
 * @注释
 */
public class LeetCode450 {

    @Test
    public void test() {
        new Solution().deleteNode(TreeNode.constructTreeByArray(5,3,6,2,4,null,7), 7).printTree();

    }

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root==null) return null;

            if (root.val == key) {
                // 该节点要删除
                if (root.right == null) {
                    return root.left;
                }

                if (root.left == null) {
                    return root.right;
                }

                TreeNode pre = root.right;
                while (pre.left != null) {
                    pre = pre.left;
                }
                pre.left = root.left;
                return root.right;
            }

            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }
    }
}
