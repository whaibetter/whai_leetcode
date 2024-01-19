package cn.whaifree.leetCode.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/18 10:06
 * @注释
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public static Node buildTree(Integer[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        Node root = new Node(array[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;

        while (!queue.isEmpty() && index < array.length) {
            Node current = queue.poll();

            while (index < array.length && array[index] != null) {
                Node child = new Node(array[index]);
                current.children.add(child);
                queue.offer(child);
                index++;
            }
            index++;
        }

        return root;
    }
};
