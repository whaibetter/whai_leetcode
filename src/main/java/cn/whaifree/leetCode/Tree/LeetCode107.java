package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/17 14:50
 * @注释
 */
public class LeetCode107 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});
        root.printTree();

        System.out.println(new Solution1().levelOrderBottom(root));
    }
    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> queue = new LinkedList<>();

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> e = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    e.add(pop.val);
                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                }
                // 每次都采用头插法
                res.add(0,e);
            }
            return res;
        }
    }

    class Solution1 {

        List<List<Integer>> res = new LinkedList<>();

        /**
         * 二叉树递归层次遍历
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrderBottom(TreeNode root) {

            if (root == null) {
                return res;
            }
            level(root, 0);
            Collections.reverse(res);
            return res;
        }

        public void level(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            int size = res.size();
            if (size <= level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(root.val);
            level(root.left, level + 1);
            level(root.right, level + 1);
        }
    }

}
