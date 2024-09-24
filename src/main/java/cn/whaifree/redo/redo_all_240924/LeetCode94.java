package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 11:40
 * @注释
 */
public class LeetCode94 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{2, 3, 6, 8, 9});
        System.out.println(new Solution().inorderTraversal(treeNode));
    }

    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }

            List<Integer> list = new ArrayList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            deque.push(root);
            while (!deque.isEmpty()) {
                TreeNode pop = deque.pop();
                if (pop != null) {
                    if (pop.left != null) {
                        deque.push(pop.left);
                    }
                    if (pop.right != null) {
                        deque.push(pop.right);
                    }
                    deque.push(pop);
                    deque.push(null);

                }else {
                    list.add(deque.pop().val);
                }
            }
            Collections.reverse(list);
            return list;
        }
    }

    @Test
    public void test2() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{2, 3, 6, 8, 9});
        System.out.println(Level.levelOrder(treeNode));
    }


    class Level{
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            while (!deque.isEmpty()) {

                int size = deque.size();
                ArrayList<Integer> e = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = deque.pop();
                    if (pop.left != null) {
                        deque.add(pop.left);
                    }
                    if (pop.right != null) {
                        deque.add(pop.right);
                    }
                    e.add(pop.val);
                }
                res.add(e);

            }

            return res;
        }

    }
}
