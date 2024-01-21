package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/21 20:36
 * @注释
 */
public class LeetCode111 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 2, 3, null,null,null,3});
        treeNode.printTree();
        System.out.println(new Solution2().minDepth(treeNode));
    }

    class Solution {
        int minDepth = Integer.MAX_VALUE;
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            getDepth(root, 1);
            return minDepth;
        }

        void getDepth(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                getDepth(root.left, level + 1);
            }
            if (root.right != null) {
                getDepth(root.right, level + 1);
            }
            // 左右子树都为空，才表明已经到底部了
            if (root.left == null && root.right == null && level < minDepth) {
                minDepth = level;
            }
        }
    }

    class Solution1 {
        public int minDepth(TreeNode root) {
            return getDepth(root);
        }

        int getDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int right = getDepth(root.right) ;
            int left = getDepth(root.left) ;
            if (root.left == null && root.right != null) {
                return right + 1;
            }
            if (root.right == null && root.left != null) {
                return left + 1;
            }

            return 1 + Math.min(right, left);
        }
    }


    class Solution2 {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int depth = 0;

            // 右视图
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    TreeNode pop = deque.pop();
                    if (pop.left != null) {
                        deque.add(pop.left);
                    }
                    if (pop.right != null) {
                        deque.add(pop.right);
                    }
                    if (pop.right == null && pop.left == null) {
                        // 从上到下第一个双节点非空就是输出
                        return depth;
                    }
                }

            }
            return 0;
        }

    }
}
