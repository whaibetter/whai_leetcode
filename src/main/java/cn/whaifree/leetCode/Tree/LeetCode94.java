package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.ListNode;
import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/15 19:29
 * @注释
 */
public class LeetCode94 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 4});
        TreeNode.printTree(root);

        System.out.println(new Solution2().inorderTraversal(root));
        System.out.println(new Solution2().preorderTraversal(root));
        System.out.println(new Solution2().postorderTraversal(root));


    }

    class Solution {
        List<Integer> res = new LinkedList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return res;
            }
            inorder(root);
            return res;
        }

        public void inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            res.add(root.val);
            inorder(root.right);
        }

    }

    class Solution1 {


        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> list = new LinkedList<Integer> ();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            TreeNode index = root;
            while (!stack.isEmpty() || index != null) {
                // 不断左边加入，弹出获取右边的
                // 每次循环处理一次节点，左边，右边和中间
                if (index != null) {
                    stack.push(index);
                    index = index.left;
                } else {
                    index = stack.pop();
                    list.add(index.val);
                    index = index.right;
                }
            }
            return list;
        }

    }

    class Solution2 {


        /**
         * 不能 前后序统一
         * 因为中间节点是在中间，每次经过他都不能判断是否是第一次进入
         * - 如果增加一个标记，标记Null入栈，判断到null时就证明他是已经走过的节点
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> list = new LinkedList<Integer> ();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            /**
             * 右边进栈，再左边进栈，中间用空指针标记
              */
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                // 这个if处理中间节点是否处理过
                if (pop != null) {
                    if(pop.right!=null) stack.push(pop.right);
                    // 此处null 为标记已经处理过的节点，下次遇到这个节点就可以直接输出了
                    stack.push(pop);
                    stack.push(null);
                    if (pop.left!=null) stack.push(pop.left);
                } else {
                    TreeNode pop1 = stack.pop();
                    list.add(pop1.val);
                }

            }
            return list;
        }


        /**
         * 不能 前后序统一
         * 因为中间节点是在中间，每次经过他都不能判断是否是第一次进入
         * - 如果增加一个标记，标记Null入栈，判断到null时就证明他是已经走过的节点
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {

            List<Integer> list = new LinkedList<Integer> ();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            /**
             * 右边进栈，再左边进栈，中间用空指针标记
             */
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();

                if (pop != null) {
                    // 变更这三个的顺序就能 前中后序
                    if (pop.right != null) {
                        stack.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack.push(pop.left);
                    }
                    stack.push(pop);
                    stack.push(null);
                } else {
                    TreeNode pop1 = stack.pop();
                    list.add(pop1.val);
                }

            }
            return list;
        }

        public List<Integer> postorderTraversal(TreeNode root) {

            List<Integer> list = new LinkedList<Integer> ();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new LinkedList<>();

            stack.push(root);
            /**
             * 右边进栈，再左边进栈，中间用空指针标记
             */
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();

                if (pop != null) {

                    stack.push(pop);
                    stack.push(null);
                    if (pop.right != null) {
                        stack.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack.push(pop.left);
                    }

                } else {
                    TreeNode pop1 = stack.pop();
                    list.add(pop1.val);
                }

            }
            return list;

        }

    }


}
