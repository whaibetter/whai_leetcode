package cn.whaifree.redo.redo.redo_24_1_20;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/20 18:37
 * @注释
 */
public class LeetCode94 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 4});
        root.printTree();
        System.out.println(new Solution().inorderTraversal(root));
    }

    class Solution{
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {

                TreeNode pop = stack.pop();
                if (pop != null) {
                    // 中序
//                    stack.push(pop);
//                    stack.push(null);
//                    if (pop.left != null) {
//                        stack.push(pop.left);
//                    }
//                    if (pop.right != null) {
//                        stack.push(pop.right);
//                    }

                    // 后序 再Reverse
                    stack.push(pop);
                    stack.push(null);
                    if (pop.right != null) {
                        stack.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack.push(pop.left);
                    }

                    // 前序
//                    if (pop.right != null) {
//                        stack.push(pop.right);
//                    }
//                    if (pop.left != null) {
//                        stack.push(pop.left);
//                    }
//                    stack.push(pop);
//                    stack.push(null);

                } else {
                    // 前序 中序
//                    res.add(stack.pop().val);
                    // 后序
                    res.add(stack.pop().val);
                }

            }
            return res;

        }

    }
}
