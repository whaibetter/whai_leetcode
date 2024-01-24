package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import javafx.scene.layout.VBox;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/24 19:35
 * @注释
 */
public class LeetCode513 {
    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1});
        root.printTree();
        System.out.println(new Solution1().findBottomLeftValue(root));
    }

    class Solution {
        public int findBottomLeftValue(TreeNode root) {
            if (root == null) {
                return 0;
            }

            ArrayList<List<Integer>> e = new ArrayList<>();
            Deque<TreeNode> deque = new java.util.LinkedList<>();
            deque.add(root);
            int res = 0;
            while (!deque.isEmpty()) {
                int size = deque.size();
                while (size-- > 0) {
                    TreeNode pop = deque.pop();
                    if (pop.right!=null) deque.add(pop.right);
                    if (pop.left!=null) deque.add(pop.left);
                    if (deque.isEmpty()) {
                        res = pop.val;
                    }
                }
            }
            return res;

        }
    }

    class Solution1 {
        Integer depth = 0;
        Integer res = 0;
        public int findBottomLeftValue(TreeNode root) {
            // 只有一个节点
            if (root.left==null&&root.right==null) return root.val;
            level(root, 0);
            return res;
        }

        public void level(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            if (root.right == null && root.left == null) {
                if (level > depth) {
                    depth = level;
                    res = root.val;
                }
                // 遇到每层层第一个叶子节点，就可能是叶子节点，如果层数更深，那么就替换
                return;
            }
            level(root.left, level + 1);
            level(root.right, level + 1);
        }
    }
}
