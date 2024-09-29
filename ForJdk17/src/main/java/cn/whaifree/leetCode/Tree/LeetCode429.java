package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.Node;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/18 10:05
 * @注释
 */
public class LeetCode429 {


    @Test
    public void test() {
        Node node = new Node(1);
        node.children = new LinkedList<>();
        node.children.add(new Node(2));
        node.children.add(new Node(3));
        node.children.add(new Node(4));
        node.children.get(2).children = new ArrayList<>();
        node.children.get(2).children.add(new Node(6));
        node.children.get(2).children.add(new Node(7));

        List<List<Integer>> lists = new Solution1().levelOrder(node);
        System.out.println(lists);

    }

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {


            List<List<Integer>> res = new LinkedList<>();

            if (root == null) {
                return res;
            }
            Deque<Node> queue = new LinkedList<>();

            queue.add(root);
            while (!queue.isEmpty()) {

                int size = queue.size();
                List<Integer> e = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node pop = queue.pop();
                    e.add(pop.val);
                    List<Node> children = pop.children;
                    if (children != null && !children.isEmpty()) {
                        children.forEach(new Consumer<Node>() {
                            @Override
                            public void accept(Node node) {
                                queue.add(node);
                            }
                        });
                    }
                }
                res.add(e);

            }
            return res;
        }
    }

    /**
     * 递归实现
     */
    class Solution1 {
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> levelOrder(Node root) {
            if (root == null) {
                return res;
            }
            level(root, 0);
            return res;
        }

        void level(Node root, int level) {

            if (root == null) {
                return;
            }
            // 没有对应的层数值就创建
            if (res.size() <= level) {
                res.add(new ArrayList<>());
            }
            List<Integer> e = res.get(level);
            e.add(root.val);
            List<Node> children = root.children;
            if (children != null) {
                children.forEach(
                        node -> level(node, level + 1)
                );
            }
        }
    }
}
