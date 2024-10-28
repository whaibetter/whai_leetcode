package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/15 20:11
 * @注释
 */
public class LeetCode145 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5});

        root.printTree();
        new Solution1().postorderTraversal(root).forEach(
                System.out::println
        );
    }

    class Solution {
        List<Integer> res = new LinkedList<>();

        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return res;
            }
            postorder(root);
            return res;
        }

        public void postorder(TreeNode root) {
            if (root==null) return;

            postorder(root.left);
            postorder(root.right);
            res.add(root.val);

        }
    }


    class Solution1 {
        public List<Integer> postorderTraversal(TreeNode root) {

            List<Integer> res = new LinkedList<Integer>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();

            stack.push(root);
            while (!stack.isEmpty()) {

                TreeNode pop = stack.pop();
                res.add(pop.val);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }

            Collections.reverse(res);

            return res;
        }

    }

}
