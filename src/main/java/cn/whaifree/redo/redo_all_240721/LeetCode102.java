package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/25 22:45
 * @注释
 */
public class LeetCode102 {
    public static void main(String[] args) {

        TreeNode treeNode = TreeNode.constructTreeByArray(3, 9, 20, null, null, 15, 7);
        for (List<Integer> integers : levelOrder1(treeNode)) {
            System.out.println(integers);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = deque.pop();
                list.add(pop.val);
                if (pop.left != null) {
                    deque.add(pop.left);
                }
                if (pop.right != null) {
                    deque.add(pop.right);
                }
            }
            res.add(list);
        }


        return res;
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        level(root, 0, res);
        return res;
    }

    public static void level(TreeNode root, int inLevel, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() == inLevel) {
            res.add(new ArrayList<>());
        }
        res.get(inLevel).add(root.val);
        level(root.left, inLevel + 1, res);
        level(root.right, inLevel + 1, res);
    }

}
