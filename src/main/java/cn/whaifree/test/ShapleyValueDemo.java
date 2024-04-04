package cn.whaifree.test;

import java.util.ArrayList;
import java.util.List;

public class ShapleyValueDemo {

    static class Node {
        int id;
        double distance;
        double strength;

        Node(int id, double distance, double strength) {
            this.id = id;
            this.distance = distance;
            this.strength = strength;
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        // 添加三个节点的信息，这里以节点的 ID、距离和强度为例
        nodes.add(new Node(1, 0.5, 0.8));
        nodes.add(new Node(2, 0.8, 0.6));
        nodes.add(new Node(3, 0.7, 0.7));

        List<Double> shapleyValues = calculateShapleyValues(nodes);

        System.out.println("Shapley Values:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("Node " + nodes.get(i).id + ": " + shapleyValues.get(i));
        }
    }

    public static List<Double> calculateShapleyValues(List<Node> nodes) {
        List<Double> shapleyValues = new ArrayList<>();
        double[] marginalContributions = new double[nodes.size()];

        // 计算每个节点的 Shapley 值
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    double valueWithI = getValue(getPlayerSubset(nodes.get(i), nodes.get(j)));
                    double valueWithoutI = getValue(getPlayerSubset(nodes.get(j)));
                    marginalContributions[i] += (valueWithI - valueWithoutI);
                }
            }
            shapleyValues.add(marginalContributions[i] / nodes.size());
        }

        return shapleyValues;
    }

    // 模拟每个节点的贡献价值
    public static double getValue(List<Node> nodes) {
        // 这里可以根据实际情况编写计算价值的逻辑，这里简单地假设节点的贡献价值为距离与强度的乘积之和
        double value = 0.0;
        for (Node node : nodes) {
            value += node.distance * node.strength;
        }
        return value;
    }

    // 生成不包含给定节点的组合
    public static List<Node> getPlayerSubset(Node excludedNode, Node... nodes) {
        List<Node> subset = new ArrayList<>();
        for (Node node : nodes) {
            if (node != excludedNode) {
                subset.add(node);
            }
        }
        return subset;
    }
}
