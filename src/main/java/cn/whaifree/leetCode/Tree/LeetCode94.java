package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.ListNode;
import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/15 19:29
 * @注释
 */
public class LeetCode94 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5});
        TreeNode.printTree(root);

        System.out.println(new Solution1().inorderTraversal(root));
    }

    class Solution {
        List<Integer> res = new LinkedList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return res;
            }
            inorder(root);
            return res;
        }

        public void inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            res.add(root.val);
            inorder(root.right);
        }

    }

    class Solution1 {


        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> list = new LinkedList<Integer> ();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            TreeNode index = root;
            while (!stack.isEmpty() || index != null) {
                // 不断左边加入，弹出获取右边的
                // 每次循环处理一次节点，左边，右边和中间
                if (index != null) {
                    stack.push(index);
                    index = index.left;
                } else {
                    index = stack.pop();
                    list.add(index.val);
                    index = index.right;
                }
            }
            return list;
        }

    }
}
