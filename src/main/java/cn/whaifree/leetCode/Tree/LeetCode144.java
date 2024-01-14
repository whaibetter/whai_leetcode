package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 前序遍历
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/14 15:12
 * @注释
 */
public class LeetCode144 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{});
        TreeNode.printTree(treeNode);
        new Solution1().preorderTraversal(treeNode).forEach(
                integer -> System.out.println(integer)
        );
    }


    class Solution {

        List<Integer> res = new LinkedList<Integer>();
        public List<Integer> preorderTraversal(TreeNode root) {

            if (root == null) {
                return res;
            }
            return pre(root);
        }

        public List<Integer> pre(TreeNode root) {
            if (root == null) {
                return null;
            }
            res.add(root.val);
            pre(root.left);
            pre(root.right);
            return res;
        }
    }

    class Solution1 {


        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<Integer>();
            if (root == null) {
                return res;
            }
            // 基于指针
            Deque<TreeNode> queue = new LinkedList<>();
            // 加入根节点
            TreeNode index = root;
            queue.push(index);
            while (!queue.isEmpty()) {
                index = queue.pop();
                res.add(index.val);
                // 先加入右子树，因为是栈，后出右边
                if (index.right != null) {
                    queue.push(index.right);
                }
                // 后加入左子树
                if (index.left != null) {
                    queue.push(index.left);
                }
            }
            return res;
        }

    }


}
