package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 11:53
 * @注释
 */
public class LeetCode101 {
    @Test
    public void test()
    {
        System.out.println(new Solution1().isSymmetric(TreeNode.constructTreeByArray(1,2,3)));
    }

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isSyn(root.left, root.right);
        }

        public boolean isSyn(TreeNode one, TreeNode two) {
            if (one == null && two == null) {
                return true;
            } else if (one == null || two == null) {
                return false;
            } else if (one.val != two.val) {
                return false;
            }

            boolean a = isSyn(one.left, two.right);
            boolean b = isSyn(one.right, two.left);

            return a && b;
        }
    }

    class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            if (root.left == null && root.right == null) {
                return true;
            }else if (root.left == null || root.right == null) {
                return false;
            }

            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root.left);
            deque.add(root.right);
            while (!deque.isEmpty()) {
                TreeNode one = deque.pop();
                TreeNode two = deque.pop();
                if (one != null && two == null) {
                    return false;
                } else if (one == null && two != null) {
                    return false;
                } else if (one == null && two == null) {
                    continue;
                }
                if (one.val != two.val) {
                    return false;
                }
                deque.add(one.left);
                deque.add(two.right);
                deque.add(one.right);
                deque.add(two.left);
            }

            return true;
        }

    }

}
