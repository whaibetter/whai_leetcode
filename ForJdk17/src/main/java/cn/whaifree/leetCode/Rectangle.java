package cn.whaifree.leetCode;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/6 15:26
 * @注释
 */
public class Rectangle {
    public static void main(String[] args) {
        // 假设给定的三个点 A, B, C
//        int[] A = {4, 2};
//        int[] B = {2, 4};
//        int[] C = {4, 6};
        // 测试数据
        Point A = new Point(2, 4);
        Point B = new Point(4, 2);
        Point C = new Point(4, 6);



        // 计算第四个点
        Point D = calculateFourthPoint(A, B, C);

        // 输出结果
        System.out.printf("第四个点 D: (%.2f, %.2f)%n", D.x, D.y);
    }


    // 定义点类
    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // 计算第四个点的方法
    public static Point calculateFourthPoint(Point A, Point B, Point C) {
        // 使用向量运算计算第四个点
        double x4 = A.x + B.x + C.x - (A.x + B.x);
        double y4 = A.y + B.y + C.y - (A.y + B.y);

        // 这里的 x4 和 y4 需要根据相邻点的选择进行调整
        // 假设 A 和 B 是相邻点，C 是对角点
        return new Point(A.x + C.x - B.x, A.y + C.y - B.y);
    }


    @Test
    public void test() {
        in(TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
    }

    public void in(TreeNode treeNode) {

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {


                // 前序
//                if (pop.right != null) {
//                    stack.push(pop.right);
//                }
//                if (pop.left != null) {
//                    stack.push(pop.left);
//                }
//                stack.push(pop);
//                stack.push(null);

                // 后序
//                stack.push(pop);
//                stack.push(null);
//                if (pop.right != null) {
//                    stack.push(pop.right);
//                }
//                if (pop.left != null) {
//                    stack.push(pop.left);
//                }

                // 中序列
//                if (pop.right != null) {
//                    stack.push(pop.right);
//                }
//                stack.push(pop);
//                stack.push(null);
//                if (pop.left != null) {
//                    stack.push(pop.left);
//                }

            }else {
                TreeNode item = stack.pop();
                System.out.println(item.val);
            }
        }
    }
    @Test
    public void test1() {
        List<Object> classes = new ArrayList<>();
        int counter = 0;

        while (true) {
            // 动态生成匿名内部类并加载
            Class<?> anonymousClass = new Object() {}.getClass();
            classes.add(anonymousClass);
            counter++;

            // 每生成1000个类输出一次计数
            if (counter % 1000 == 0) {
                System.out.println("Generated " + counter + " classes");
            }
        }
    }

    @Test
    public void test2() {
        Node node = new Node(1, List.of(new Node(3, null), new Node(2, null), new Node(4, null)));
        in2(node);
    }
    static class Node{
        int val;
        List<Node> child;

        public Node(int val, List<Node> child) {
            this.val = val;
            this.child = child;
        }

    }
    public void in2(Node treeNode) {
        Deque<Node> stack = new LinkedList<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop != null) {
                stack.push(pop);
                stack.push(null);
                if (pop.child != null) {
                    for (Node node : pop.child) {
                        stack.push(node);
                    }
                }
            }else {
                Node item = stack.pop();
                System.out.println(item.val);
            }
        }
    }

}
