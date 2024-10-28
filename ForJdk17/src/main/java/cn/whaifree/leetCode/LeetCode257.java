package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.Node;
import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/22 21:54
 * @注释
 */
public class LeetCode257 {

    @Test
    public void test() {

        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 5,6,7,8,9,0,6});
        root.printTree();

        System.out.println(new Solution3().binaryTreePaths(root));
    }
    class Solution {

        List<String> res = new ArrayList<String>();

        public List<String> binaryTreePaths(TreeNode root) {
            if (root == null) {
                return res;
            }
            deal(root, "");
            return res;
        }

        public void deal(TreeNode root, String s) {

            if (root == null) return;
            if (root.left == null && root.right == null) {
                res.add(new StringBuilder(s).append(root.val).toString());
            }
            String tmp = new StringBuilder(s).append(root.val).append("->").toString();
            deal(root.left, tmp);
            deal(root.right, tmp);
        }

    }


    class Solution1 {

        List<String> res = new ArrayList<String>();

        public List<String> binaryTreePaths(TreeNode root) {
            if (root == null) {
                return res;
            }
            deal(root, "");
            return res;
        }

        public void deal(TreeNode treeNode, String s) {

            if (treeNode==null) return;
            //根节点
            if (treeNode.right == null && treeNode.left == null) {
                res.add(s + treeNode.val);
//                res.add(new StringBuilder().append(s).append(treeNode.val).toString());
            }

//            String tmp = new StringBuilder().append(s).append("->").append(treeNode.val).toString();
            String tmp = s + treeNode.val + "->";
            deal(treeNode.left, tmp);
            deal(treeNode.right, tmp);
        }



    }

    class Solution2 {

        List<String> res = new ArrayList<String>();
        List<Integer> route = new ArrayList<Integer>();


        public List<String> binaryTreePaths(TreeNode root) {
            if (root == null) {
                return res;
            }
            deal(root);
            return res;
        }

        public void deal(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            route.add(treeNode.val);
            if (treeNode.left == null && treeNode.right == null) {
                // 根节点
                // 可以知道结果了
                StringBuilder stringBuilder = new StringBuilder();
                int size = route.size();
                for (int i = 0; i < size - 1; i++) {
                    stringBuilder.append(route.get(i)).append("->");
                }
                stringBuilder.append(route.get(size - 1));
                res.add(stringBuilder.toString());
                return;
            }
            if (treeNode.right != null) {
                deal(treeNode.right);
                // 回溯
                route.remove(route.size() - 1);
            }
            if (treeNode.left != null) {
                deal(treeNode.left);
                route.remove(route.size() - 1);
            }

        }



    }

    class Solution3 {


        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<String>();

            if (root == null) {
                return res;
            }

            Deque<Object> stack = new ArrayDeque<>();
            stack.add(root);
            stack.add(root.val + "");
            while (!stack.isEmpty()) {
                TreeNode nodePeek = (TreeNode) stack.pop();
                String s = (String) stack.pop();
                // 叶子节点
                if (nodePeek.left == null && nodePeek.right == null) {
//                    s += "->" + nodePeek.val;
                    res.add(s);
                }

                // 左右进栈
                if (nodePeek.left != null) {
                    stack.add(nodePeek.left);
                    stack.add(new StringBuilder().append(s).append("->").append(nodePeek.left.val).toString());
                }
                if (nodePeek.right != null) {
                    stack.add(nodePeek.right);
                    stack.add(new StringBuilder().append(s).append("->").append(nodePeek.right.val).toString());
                }
            }

            return res;
        }



    }


}
