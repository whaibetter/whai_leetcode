package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 9:38
 * @注释
 */
public class LeetCode450 {

    @Test
    public void test() {
        new Solution1().deleteNode(TreeNode.constructTreeByArray(5,3,6,2,4,null,7,null,null), 0).printTree();

    }

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }

            if (root.left == null && root.right == null && root.val == key) {
                return null;
            }


            TreeNode pre = null;
            TreeNode tmp = root;
            while (tmp!=null) {
                if (tmp.val > key) {
                    pre = tmp;
                    tmp = tmp.left;
                } else if (tmp.val < key) {
                    pre = tmp;
                    tmp = tmp.right;
                } else {
                    break;
                }
            }

            // 找不到该要删除的节点
            if (pre != null && pre.val != key && tmp == null) {
                return root;
            }



            // 左右都为空
            // 左为空
            // 右为空
            if (pre.left == tmp) {
                if (tmp.right == null && tmp.left == null) {
                    pre.left = null;
                } else if (tmp.left == null) {
                    pre.left = tmp.right;
                } else if (tmp.right == null) {
                    pre.left = tmp.left;
                }
            }else if (pre.right == tmp){
                if (tmp.right == null && tmp.left == null) {
                    pre.right = null;
                } else if (tmp.left == null) {
                    pre.right = tmp.right;
                } else if (tmp.right == null) {
                    pre.right = tmp.left;
                }
            }
            // 左右都不空
            // tmp.left移动到tmp.right.left.....最后
            TreeNode move = tmp.right;
            while (move.left != null) {
                move = move.left;
            }
            move.left = tmp.left;
            // tmp.right.left移动到tmp的位置 有可能在pre的左边或右边
            if (pre.left == tmp) {
                pre.left = tmp.right;
            } else {
                pre.right = tmp.right;
            }
            return root;
        }
    }


    class Solution1 {
        /**
         * 递归
         * @param root
         * @param key
         * @return
         */
        public TreeNode deleteNode(TreeNode root, int key) {

            if (root == null) {
                return root;
            }
            if (root.val == key) {

                // 左边为空 返回右边
                // 两边都为空，则返回空，随便返回子节点
                if (root.left == null) {
                    return root.right;
                }
                if (root.right == null) {
                    return root.left;
                }
                TreeNode index = root.right;
                while (index.left != null) {
                    index = index.left;
                }
                index.left = root.left;
                return root.right;
            }

            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }
    }

    /**
     * 把二叉树变成链表
     */
    class Solution2 {
        TreeNode pre = new TreeNode();
        TreeNode cur = pre;

        public TreeNode deleteNode(TreeNode root, int key) {
            search(root, key);
            return pre.right;
        }

        private void search(TreeNode root, int key) {
            if (root == null) {
                return;
            }
            search(root.left, key);
            TreeNode r = root.right;
            if (root.val != key) {
                cur.right = root;
                root.left = null;
                root.right = null;
                cur = cur.right;
            }
            search(r, key);

        }
    }
}
