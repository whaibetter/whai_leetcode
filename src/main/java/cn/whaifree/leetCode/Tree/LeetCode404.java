package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/24 19:03
 * @注释
 */
public class LeetCode404 {

    @Test
    public void test(){
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});
        treeNode.printTree();
        System.out.println(new Solution2().sumOfLeftLeaves(treeNode));
    }

    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int right = sumOfLeftLeaves(root.right);
            int left = sumOfLeftLeaves(root.left);

            // 如果左边存在，且是叶子节点，再返回
            if (root.left != null && root.left.left == null && root.left.right == null) {
                return left + right + root.left.val;
            }
            return left + right;
        }
    }

    class Solution1 {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            // 遍历
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop != null) {
                    // 遇到没进入过的节点
                    if (pop.right!=null) stack.push(pop.right);
                    if (pop.left!=null) stack.push(pop.left);
                    stack.push(pop);
                    stack.push(null);

                } else {
                    TreeNode pop1 = stack.pop();
//                    if (pop1.left.left == null && pop1.left.right == null) {
//
//                    }
                }
            }
            return 1;
        }
    }

    class Solution2 {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Deque<TreeNode> deque= new LinkedList<>();
            deque.add(root);
            // 遍历
            int sum = 0;
            while (!deque.isEmpty()) {
                TreeNode pop = deque.pop();
                if (pop.right != null) {
                    deque.add(pop.right);
                }
                if (pop.left != null) {
                    // left是叶子节点，并且是左边
                    if (pop.left.right == null && pop.left.left == null) {
                        sum += pop.left.val;
                    }
                    deque.add(pop.left);
                }

            }
            return sum;
        }
    }
}

