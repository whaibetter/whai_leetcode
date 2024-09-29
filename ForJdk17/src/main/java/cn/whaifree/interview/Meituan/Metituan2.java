package cn.whaifree.interview.Meituan;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/7 10:02
 * @注释
 */
public class Metituan2 {

}

class mt202{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        long[] nums = new long[i];
        for (int i1 = 0; i1 < nums.length; i1++) {
            nums[i1] = in.nextInt();
        }
        Arrays.sort(nums);
        method(nums);

    }

    /**
     *
     * @param nums
     */
    public static void method(long[] nums) {
        long minP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                minP += nums[i] + 1;
            }
        }
        long minM = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                minM += nums[i] + 1;
            }
        }
        System.out.println(Math.min(minM, minP));
    }


}

class mt203 {

    static class TreeNode {
        int now;
        List<TreeNode> sonList;

        public TreeNode(int now, List<TreeNode> sonList) {
            this.now = now;
            this.sonList = sonList;
        }

        public int getNow() {
            return now;
        }

        public void setNow(int now) {
            this.now = now;
        }

        public List<TreeNode> getSonList() {
            return sonList;
        }

        public void setSonList(List<TreeNode> sonList) {
            this.sonList = sonList;
        }
    }

    static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        for (int i = 0; i < c; i++) {
            int num = in.nextInt();
            for (int j = 0; j < num; j++) {
                int start = in.nextInt();
                int end = in.nextInt();
                if (map.containsKey(start)) {
                    map.get(start).add(end);
                }else {
                    ArrayList<Integer> value = new ArrayList<>();
                    value.add(end);
                    map.put(start, value);
                }
            }
        }

        // key 边的数量,
        Map<Integer, Integer> nMap = new HashMap<>();
        // 统计每个list的size
        map.forEach(new BiConsumer<Integer, List<Integer>>() {
            @Override
            public void accept(Integer integer, List<Integer> integers) {

            }
        });

    }
}

class cc{
    /**
     * 1
     * 6
     * 1 2
     * 1 3
     * 3 7
     * 2 4
     * 2 6
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // key 起始点 v 数量 每个节点对应子节点数量
        Map<Integer, Integer> map = new HashMap<>();
        int c = in.nextInt();
        for (int i = 0; i < c; i++) {
            int num = in.nextInt();
            for (int j = 0; j < num - 1; j++) {
                int start = in.nextInt();
                int end = in.nextInt();
                map.put(start, map.getOrDefault(start, 0) + 1);
            }
            int leafSize = num - map.size();
            // key 节点数量 value几个节点有k这么多的子节点
            Map<Integer, Integer> newMap = new HashMap<>();
            for (Integer key : map.keySet()) {
                Integer v = map.get(key);
                newMap.put(v, newMap.getOrDefault(v, 0) + 1);
            }

            int res = 0;
            for (Integer k : newMap.keySet()) {
                Integer hasSize = newMap.get(k);
                if (hasSize > 1) {
                    res+= hasSize;
                }
            }
            // 组合
            res = res * (res - 1) / 2;
            leafSize = leafSize * (leafSize - 1) / 2;
            System.out.println(res + leafSize);
        }



    }
}
class dd{

    static HashMap<Integer, List<Integer>> graph = new HashMap<>();
    static HashMap<Integer, Integer> childCount = new HashMap<>();
    static HashMap<Integer, Integer> countMap = new HashMap<>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        int c = in.nextInt();
        for (int i = 0; i < c; i++) {
            int num = in.nextInt();
            int[][] path = new int[num][2];
            for (int j = 0; j < num - 1; j++) {
                int start = in.nextInt();
                int end = in.nextInt();
                path[j][0] = start;
                path[j][1] = end;
            }

            for (int[] edge : path) {
                if (!graph.containsKey(edge[0])) {
                    graph.put(edge[0], new ArrayList<>());
                }
                graph.get(edge[0]).add(edge[1]);
            }

            dfs(1);
            int pairCount = 0;
            for (Integer value : countMap.values()) {
                if (value > 1) {
                    pairCount += value * (value - 1) / 2;
                }
            }
            System.out.println(pairCount);
        }
    }


    public static void dfs(int node) {

        int count = 0;
        if (graph.containsKey(node)) {
            for (Integer child : graph.get(node)) {
                count += graph.get(node).size();
                dfs(child);
            }
        }
        count++;
        childCount.put(node, count);
        countMap.put(count, countMap.getOrDefault(count, 0) + 1);

    }
}

