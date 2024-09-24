package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/23 17:31
 * @注释
 */
public class LeetCode114 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        new Solution().flatten(treeNode);
        treeNode.printTree();
    }


    class Solution {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            Deque<TreeNode> deque = new LinkedList<>();
            deque.push(root);
            TreeNode tmp = root;
            while (!deque.isEmpty()) {
                TreeNode pop = deque.pop();
                if (pop != null) {
                    if (pop.right != null) {
                        deque.push(pop.right);
                    }
                    if (pop.left != null) {
                        deque.push(pop.left);
                    }
                    deque.push(pop);
                    deque.push(null);
                }else {
                    TreeNode nextPop = deque.pop();
                    if (nextPop != tmp) {
                        tmp.right = nextPop;
                        tmp.left = null;
                        tmp = tmp.right;
                    }
                }
            }
        }
    }

    @Test
    public void test2() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{0, 1, 2, 3, 4, 5, 6});
        new Solution2().flatten(treeNode);
        treeNode.printTree();
    }

    class Solution2 {
        public void flatten(TreeNode root) {



            if (root == null) {
                return;
            }
            subFlatten(root);
        }

        public TreeNode subFlatten(TreeNode root) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode tmp = root;
            root.left = null;
            if (left != null) {
                root.right = left;
                tmp = subFlatten(left);  // tmp会返回一节的最后一个尾巴节点
            }
            if (right != null) {
                tmp.right = right; // 把前面的right拿过来再往下
                tmp = subFlatten(right);
            }
            return tmp;
        }
    }

}
