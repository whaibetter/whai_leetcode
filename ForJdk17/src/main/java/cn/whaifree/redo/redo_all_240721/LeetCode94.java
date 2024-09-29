package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/24 22:37
 * @注释
 */
public class LeetCode94 {


    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.constructTreeByArray();
        List<Integer> integers = inorderTraversal(treeNode);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }


    /**
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                stack.push(pop);
                stack.push(null);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }else {
                res.add(stack.pop().val);
            }

        }
        return res;
    }
}
