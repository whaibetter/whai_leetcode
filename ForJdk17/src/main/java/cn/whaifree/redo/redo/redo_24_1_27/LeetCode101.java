package cn.whaifree.redo.redo.redo_24_1_27;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/27 16:53
 * @注释
 */
public class LeetCode101 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 2, 3, null,null, 3});
        System.out.println(new Solution1().isSymmetric(treeNode));
    }

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return compare(root.left, root.right);
        }

        public boolean compare(TreeNode ATree,TreeNode BTree) {
            if (ATree== null && BTree == null) {
                return true;
            }else if(ATree != null && BTree == null) {
                return false;
            }else if (ATree == null && BTree != null) {
                return false;
            } else if (ATree.val != BTree.val) {
                return false;
            }

            boolean compare1 = compare(ATree.left, BTree.right);
            boolean compare2 = compare(ATree.right, BTree.left);
            return compare1 && compare2;
        }
    }

    class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root.right);
            deque.add(root.left);
            while (!deque.isEmpty()) {
                TreeNode a = deque.pop();
                TreeNode b = deque.pop();
                if (a== null && b == null) {
                    continue;
                }else if(a != null && b == null) {
                    return false;
                }else if (a == null && b != null) {
                    return false;
                } else if (a.val != b.val) {
                    return false;
                }
                deque.add(a.right);
                deque.add(b.left);
                deque.add(a.left);
                deque.add(b.right);
            }

            return true;
        }




    }


}
