package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 22:42
 * @注释
 */
public class LeetCode173 {

    class BSTIterator1 {

        List<Integer> list;
        Integer index = -1;

        public BSTIterator1(TreeNode root) {
            list = new ArrayList<>();
            inOrderTraversal(root);
        }

        public int next() {
            index++;
            return list.get(index);
        }

        public boolean hasNext() {
            return index < list.size() - 1;
        }

        public void inOrderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrderTraversal(root.left);
            list.add(root.val);
            inOrderTraversal(root.right);
        }
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.constructTree(new Integer[]{7, 3, 15, null, null, 9, 20});
        BSTIterator bstIterator = new BSTIterator(root);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }

    class BSTIterator {
        /**
         * next访问到某个节点，就让他右边入栈
         */

        Deque<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            // 每层放左边第一个
            stack = new LinkedList<>();
            TreeNode index = root;
            while (index != null) {
                stack.push(index);
                index = index.left;
            }
        }

        public int next() {
            TreeNode pop = stack.pop(); // 当前指针位置
            if (pop.right != null) { // 如果有边有，就把右边和右边的左子树都放入栈中
                TreeNode index = pop.right;
                while (index != null) {
                    stack.push(index);
                    index = index.left;
                }
            }
            return pop.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }


    class BSTIterator2 {
        /**
         * next访问到某个节点，就让他右边入栈
         */

        Deque<TreeNode> stack;
        TreeNode now;
        public BSTIterator2(TreeNode root) {
            // 每层放左边第一个
            stack = new LinkedList<>();
            now = root;
        }

        public int next() {
            while (now != null) {
                stack.push(now);
                now = now.left;
            }
            now = stack.pop();
            int val = now.val;
            now = now.right;
            return val;
        }

        public boolean hasNext() {
            return now != null ||!stack.isEmpty();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}


