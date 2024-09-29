package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LeetCode145 {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTreeByArray(1, 2, 3, 4, 5);
        TreeNode.printTree(root);
        postorderTraversal(root).forEach(
                (x) -> {
                    System.out.println(x);
                }
        );
    }

    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();

            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            res.add(pop.val);
        }
        Collections.reverse(res);
        return res;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop == null) {
                res.add(stack.pop().val);
            } else {
                stack.push(pop);
                stack.push(null);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }

        return res;
    }
}
