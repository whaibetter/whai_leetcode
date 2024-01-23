package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/23 19:56
 * @注释
 */
public class LeetCode100 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1,2,1});
        TreeNode treeNode1 = TreeNode.constructTree(new Integer[]{1,1,2});
        treeNode1.printTree();
        treeNode.printTree();
        System.out.println(new Solution1().isSameTree(treeNode, treeNode1));

    }

    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            }
            // LinkedList能存null
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(p);
            deque.add(q);
            while (!deque.isEmpty()) {
                // 防止只剩下一个元素，如果只剩下一个元素，那么一定是false
                TreeNode BPop = deque.pop();
                TreeNode APop = deque.pop();
                if (BPop == null && APop == null) {
                    // 叶子节点，不需要加入左右子节点
                    continue;
                } else if (BPop != null && APop == null) {
                    return false;
                } else if (BPop == null && APop != null) {
                    return false;
                } else if (BPop.val != APop.val) {
                    return false;
                }

                deque.add(BPop.right);
                deque.add(APop.right);
                deque.add(BPop.left);
                deque.add(APop.left);
            }
            return true;
        }

    }
    class Solution1 {
        public boolean isSameTree(TreeNode p, TreeNode q) {

            if (p == null && q == null) {
                return true;
            } else if (p == null && q != null) {
                return false;
            } else if (p != null && q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            }
            boolean sameTreeRight = isSameTree(p.right, q.right);
            boolean sameTreeLeft = isSameTree(p.left, q.left);
            return sameTreeLeft && sameTreeRight;
        }
    }

}
