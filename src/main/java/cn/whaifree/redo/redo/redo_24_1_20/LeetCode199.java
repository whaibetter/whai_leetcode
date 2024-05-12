package cn.whaifree.redo.redo.redo_24_1_20;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/20 19:01
 * @注释
 */
public class LeetCode199 {
    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, null, 5, null, 4});
        root.printTree();
        List<Integer> integers = new Solution1().rightSideView(root);
        System.out.println(integers);
    }

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    if (i == size - 1) {
                        res.add(pop.val);
                    }
                    if (pop.left!=null) queue.add(pop.left);
                    if (pop.right!=null) queue.add(pop.right);
                }
            }
            return res;

        }
    }

    class Solution1 {

        List<Integer> res = new LinkedList<Integer>();

        public List<Integer> rightSideView(TreeNode root) {
            level(root, 0);
            return res;
        }

        void level(TreeNode root,int level) {
            if (root == null) {
                return;
            }

            if (level == res.size()) {
                res.add(root.val);
            }
            if (root.right != null) level(root.right, level + 1);
            if (root.left != null) level(root.left, level + 1);

        }
    }
}
