package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 19:15
 * @注释
 */
public class LeetCode538 {

    @Test
    public void test() {

        new Solution1().convertBST(TreeNode.constructTreeByArray(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8)).printTree();
    }

    class Solution {
        int sum = 0;
        public TreeNode convertBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            root.right = convertBST(root.right);
            root.val += sum;
            sum = root.val;
            root.left = convertBST(root.left);
            return root;
        }
    }


    class Solution1 {
        public TreeNode convertBST(TreeNode root) {

            if (root == null) {
                return null;
            }

            Deque<TreeNode> stack = new java.util.LinkedList<>();
            stack.push(root);


            int sum = 0;
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop == null) {
                    TreeNode ans = stack.pop();
                    ans.val += sum;
                    sum = ans.val;
                } else {
                    if (pop.left!=null) stack.push(pop.left);
                    stack.push(pop);
                    stack.push(null);
                    if (pop.right!=null) stack.push(pop.right);
                }
            }
            return root;
        }
    }
}
