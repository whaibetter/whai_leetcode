package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/30 17:35
 * @注释
 */
public class LeetCode236 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(1, 2, 3, 4, 5, 6);

        new Solution1().lowestCommonAncestor(treeNode, treeNode.left.right, treeNode.left).printTree();
    }


    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            // 先找到两个树
            // 用两个List记录路径
            // 挨个对比，找到最后一个公共的点
            ArrayList<TreeNode> e1 = new ArrayList<>();
            findNode(root, p, e1);
            ArrayList<TreeNode> e2 = new ArrayList<>();
            findNode(root, q, e2);




            int minSize = Math.min(e1.size(), e2.size());
            int index = minSize - 1;
            while (index >= 0) {
                if (e1.get(index) == e2.get(index)) {
                    return e1.get(index);
                }
                index--;
            }

            return null;
        }

        public boolean findNode(TreeNode root, TreeNode wantFindNode, List<TreeNode> path) {

            if (root==null) return false;
            path.add(root);
            if (root == wantFindNode) {
                return true;
            }

            boolean left = findNode(root.left, wantFindNode, path);
            if (left == true) {
                return true;
            }

            boolean right = findNode(root.right, wantFindNode, path);
            if (right == true) {
                return true;
            }

            path.remove(path.size() - 1);

            return left || right;
        }
    }

    class Solution1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root==null) return null;
            // 左右找到两个树
            // 如果找到树了，返回该节点
            if (root.val == p.val || root.val == q.val) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if(left == null && right == null) { // 若未找到节点 p 或 q
                return null;
            }else if(left == null && right != null) { // 若找到一个节点
                return right;
            }else if(left != null && right == null) { // 若找到一个节点
                return left;
            }else { // 若找到两个节点
                return root;
            }
        }


    }

}
