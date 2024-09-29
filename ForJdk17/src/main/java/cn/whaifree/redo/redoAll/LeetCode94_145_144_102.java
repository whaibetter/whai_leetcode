package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 17:48
 * @注释
 */
public class LeetCode94_145_144_102 {

    @Test
    public void test(
    ) {
        new Solution().levelOrder(TreeNode.constructTreeByArray(1,2,3,4,5,6)).forEach(
                System.out::println
        );
    }

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            circle(root, list);
            return list;
        }

        public void circle(TreeNode treeNode, List<Integer> list) {
            if (treeNode == null) {
                return;
            }
            circle(treeNode.left, list);
            list.add(treeNode.val);
            circle(treeNode.right, list);
        }

        public List<Integer> inorderTraversal1(TreeNode root) {

            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop == null) {
                    TreeNode index = stack.pop();
                    list.add(index.val);
                }else {
                    // 第一次进入，加入null标签
                    if (pop.right!=null) stack.push(pop.right);
                    stack.push(pop);
                    stack.push(null);
                    if (pop.left!=null) stack.push(pop.left);
                }
            }

            return list;
        }


        // 后序遍历
        public List<Integer> inorderTraversal2(TreeNode root) {

            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop == null) {
                    TreeNode index = stack.pop();
                    list.add(index.val);
                }else {
                    // 第一次进入，加入null标签
                    stack.push(pop);
                    stack.push(null);
                    if (pop.right!=null) stack.push(pop.right);
                    if (pop.left!=null) stack.push(pop.left);
                }
            }

            return list;
        }


        public List<Integer> inorderTraversal3(TreeNode root) {

            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }

            return list;
        }



        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();

            if (root == null) {
                return res;
            }
            Deque<TreeNode> deque = new LinkedList();
            deque.add(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                List<Integer> e = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = deque.pop();
                    e.add(pop.val);
                    if (pop.left!=null) deque.add(pop.left);
                    if (pop.right!=null) deque.add(pop.right);
                }
                res.add(e);
            }
            return res;
        }
    }

}
