package cn.whaifree.interview.tx;

import cn.whaifree.leetCode.model.ListNode;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 19:55
 * @注释
 */
public class tx01 {

//    public volatile Integer single = null;
//
//    public Integer getSingle() {
//        if (single == null) {
//            synchronized (Integer.class) {
//                if (single == null) {
//                    return new Integer(1);
//                }
//            }
//        }
//        return null;
//    }

    static class c extends Thread{

        @Override
        public void run() {

            System.out.println("c");

        }
    }

    public static void main(String[] args) {
        new c().start();
        Runnable runnable = () -> {
            System.out.println("1");
        };

        Callable<Object> objectCallable = new Callable<>() {
            @Override
            public Object call() throws Exception {
                System.out.println("call");
                return null;
            }
        };

        FutureTask<Object> objectFutureTask = new FutureTask<>(objectCallable);
        new Thread(objectFutureTask).start();
    }

    static class p1 {
        /**
         * 小红拿到了一个无向图，其中一些边被染成了红色。
         * 小红定义一个点是“好点”，当且仅当这个点的所有邻边都是红边。
         * 现在请你求出这个无向图“好点”的数量。
         * 注：如果一个节点没有任何邻边，那么它也是好点。
         * <p>
         * 输入描述
         * 第一行输入两个正整数n,m，代表节点的数量和边的数量。
         * 接下来的m行，每行输入两个正整数u,v和一个字符chr，代表节点u和节点v有一条边连接。如果 chr 为'R'，代表这条边被染红；'W'代表未被染色。
         * <p>
         * 1\leq n,m \leq 10^5
         * 1\leq u,v \leq n
         * <p>
         * 输出描述
         * 一个整数，代表“好点”的数量。
         * <p>
         * 示例 1
         * 收起
         * <p>
         * 输入
         * 复制
         * 4 4
         * 1 2 R
         * 2 3 W
         * 3 4 W
         * 1 4 R
         * 输出
         * 复制
         * 1
         * 说明
         * 只有 1 号节点是好点。
         *
         * @param args
         */
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            HashMap<Integer, List<int[]>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                String next = scanner.next();
                if (next.equals("R")) {
                    if (!map.containsKey(a)) {
                        map.put(a, new ArrayList<>());
                    }
                    map.get(a).add(new int[]{b, 1});

                    if (!map.containsKey(b)) {
                        map.put(b, new ArrayList<>());
                    }
                    map.get(b).add(new int[]{a, 1});
                } else {
                    if (!map.containsKey(a)) {
                        map.put(a, new ArrayList<>());
                    }
                    map.get(a).add(new int[]{b, 0});
                    if (!map.containsKey(b)) {
                        map.put(b, new ArrayList<>());
                    }
                    map.get(b).add(new int[]{a, 0});
                }
            }

            int res = 0;

            Set<Integer> integers = map.keySet();
            for (Integer key : integers) {
                boolean flag = true;
                List<int[]> ints = map.get(key);
                for (int[] anInt : ints) {
                    if (anInt[1] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) res++;
            }
            // 对没有边的
            res += (n - integers.size());

            System.out.println(res);

        }
    }

    static class p2 {

        /**
         * 小红拿到了一个链表。她准备将这个链表断裂成两个链表，再拼接到一起，使得链表从头节点到尾部升序。你能帮小红判断能否达成目的吗？
         * 给定的为一个链表数组，你需要对于数组中每个链表进行一次“是”或者“否”的答案回答，并返回布尔数组。
         * 每个链表的长度不小于 2，且每个链表中不包含两个相等的元素。所有链表的长度之和保证不超过10^5
         * <p>
         * 示例 1
         * 收起
         * <p>
         * 输入
         * 复制
         * [{1,2,3},{2,3,1},{3,2,1}]
         * 输出
         * 复制
         * [true,true,false]
         * 说明
         * 第三个链表无论怎么操作都不满足条件。
         *
         * @param args
         */
        public static void main(String[] args) {

            System.out.println(cansort(ListNode.listNodeFromArray(new int[]{1, 2, 3})));
        }

        public boolean[] canSorted(ListNode[] lists) {
            // write code here
            boolean[] res = new boolean[lists.length];
            for (int i = 0; i < lists.length; i++) {
                boolean cansort = cansort(lists[i]);
                res[i] = cansort;
            }
            return res;
        }

        public static boolean cansort(ListNode node) {
            // 4 6 1 3 2 找到两个区间都递增
            ListNode index = node;
            int flag = 0;
            while (index.next != null) {
                if (index.val > index.next.val) {
                    if (index.next.next != null && index.next.next.val > index.val) {
                        return false;
                    }
                    if (flag == 1) return false;
                    flag = 1;
                }
                index = index.next;
            }

            return true;
        }
    }

    static class p4 {
        /**
         * 小红拿到了一个有 n 个节点的无向图，这个图初始并不是连通图。
         * 现在小红想知道，添加恰好一条边使得这个图连通，有多少种不同的加边方案？
         * <p>
         * 输入描述
         * 第一行输入两个正整数n,m，用空格隔开。
         * 接下来的m行，每行输入两个正整数u,v，代表节点u和节点v之间有一条边连接。
         * 1\leq n,m \leq 10^5
         * 1\leq u,v \leq n
         * 保证给出的图是不连通的。
         * <p>
         * 输出描述
         * 一个整数，代表加边的方案数。
         * <p>
         * 示例 1
         * 收起
         * <p>
         * 输入
         * 复制
         * 4 2
         * 1 2
         * 3 4
         * 输出
         * 复制
         * 4
         * 说明
         * 添加边 (1,3) 或者 (1,4) 或者 (2,3) 或者 (2,4) 都是可以的。
         * <p>
         * 示例 2
         * 收起
         * <p>
         * 输入
         * 复制
         * 4 1
         * 1 3
         * 输出
         * 复制
         * 0
         * 说明
         * 无论添加哪条边，都不可能使得该图变成连通图。
         *
         * @param args
         */
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            HashSet<Integer> set1 = new HashSet<>();
            HashSet<Integer> set2 = new HashSet<>();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            set1.add(a);
            set1.add(b);


            for (int i = 1; i < m; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();

                if ((set1.contains(a) || set1.contains(b))) {
                    set1.add(a);
                    set1.add(b);
                } else {
                    set2.add(a);
                    set2.add(b);
                }
            }

            System.out.println(set1.size() * set2.size());

        }
    }

    static class p5 {
        /**
         * 小红拿到了一个数组，她准备将数组分割成 k 段，使得每段内部做按位异或后，再全部求和。小红希望最终这个和尽可能大，你能帮帮她吗？
         * <p>
         * 输入描述
         * 输入包含两行。
         * 第一行两个正整数 n, k , (1\leq k \leq n \leq 400)，分别表示数组的长度和要分的段数。
         * 第二行 n 个整数 a_i (0 \leq a_i \leq 10^9)，表示数组 a 的元素。
         * <p>
         * 输出描述
         * 输出一个正整数，表示最终的最大和。
         * <p>
         * 示例 1
         * 收起
         * <p>
         * 输入
         * 复制
         * 6 2
         * 1 1 1 2 3 4
         * 输出
         * 复制
         * 10
         * 说明
         * 小红将数组分为了：
         * [1, 4] 和 [5, 6] 这两个区间，得分分别为：1 \oplus 1 \oplus 1 \oplus 2 = 3 和 3 \oplus 4 = 7。总得分为 3+7=10。
         * 可以证明不存在比 10 更优的分割方案。
         * 注：\oplus 符号表示异或操作。
         * <p>
         * 示例 2
         * 收起
         * <p>
         * 输入
         * 复制
         * 5 3
         * 1 0 1 1 0
         * 输出
         * 复制
         * 3
         * 示例 3
         * 收起
         * <p>
         * 输入
         * 复制
         * 3 3
         * 1 1 2
         * 输出
         * 复制
         * 4
         *
         * @param args
         */
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }


        }

        public static int maxXorSum(int[] nums, int k) {
            int n = nums.length;
            int[][] prefixXor = new int[n + 1][k + 1];
            int[][] dp = new int[n + 1][k + 1];

            // 计算前缀异或和
            for (int i = 1; i <= n; i++) {
                prefixXor[i][0] = prefixXor[i - 1][0] ^ nums[i - 1];
            }

            for (int j = 1; j <= k; j++) {
                for (int i = 1; i <= n; i++) {
                    prefixXor[i][j] = prefixXor[i][0];
                    dp[i][j] = dp[i - 1][j];
                    for (int x = 0; x < i; x++) {
                        dp[i][j] = Math.max(dp[i][j], dp[x][j - 1] + prefixXor[i][j]);
                    }
                }
            }

            return dp[n][k];
        }
    }

    static class p6 {

        static int res = 0;
        static List<Character> path = null;

        /**
         * 小红拿到了一个字符矩阵，她可以从任意一个地方出发，希望走 6 步后恰好形成"tencent"字符串。小红想知道，共有多少种不同的行走方案？
         * 注：每一步可以选择上、下、左、右中任意一个方向进行行走。不可行走到矩阵外部。
         * <p>
         * 输入描述
         * 第一行输入两个正整数n,m，代表矩阵的行数和列数。
         * 接下来的n行，每行输入一个长度为m的、仅由小写字母组成的字符串，代表小红拿到的矩阵。
         * <p>
         * 1\leq n,m \leq 1000
         * <p>
         * 输出描述
         * 一个整数，代表最终合法的方案数。
         * <p>
         * 示例 1
         * 收起
         * <p>
         * 输入
         * 复制
         * 3 3
         * ten
         * nec
         * ten
         * 输出
         * 复制
         * 4
         * 说明
         * 第一个方案，从左上角出发，右右下左左上。
         * 第二个方案，从左上角出发，右右下左左下。
         * 第三个方案，从左下角出发，右右上左左下。
         * 第四个方案，从左上角出发，右右上左左上。
         *
         * @param args
         */
        public static void main(String[] args) {
            path = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] chars = new char[n][m];
            for (int i = 0; i < n; i++) {
                char[] c = scanner.next().toCharArray();
                for (int j = 0; j < c.length; j++) {
                    chars[i][j] = c[j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (chars[i][j] == 't') {
                        path.add('t');
                        backing(chars, i, j);
                        path.remove(path.size() - 1);
                    }
                }
            }
            System.out.println(res);

        }

        public static void backing(char[][] chars, int x, int y) {
            if (path.size() == 7) {
//                System.out.println(new ArrayList<>(path));
                res++;
                return;
            }

            Character expect = null;
            if (path.size() == 1) {
                expect = 'e';
            } else if (path.size() == 2) {
                expect = 'n';
            } else if (path.size() == 3) {
                expect = 'c';
            } else if (path.size() == 4) {
                expect = 'e';
            } else if (path.size() == 5) {
                expect = 'n';
            } else if (path.size() == 6) {
                expect = 't';
            }

            path.add(chars[x][y]);
            if (x < chars.length - 1 && expect.equals(chars[x + 1][y])) {
                backing(chars, x + 1, y);
            }
            if (y < chars[0].length - 1 && expect.equals(chars[x][y + 1])) {
                backing(chars, x, y + 1);
            }
            if (x > 0 && expect.equals(chars[x - 1][y])) {
                backing(chars, x - 1, y);
            }
            if (y > 0 && expect.equals(chars[x][y - 1])) {
                backing(chars, x, y - 1);
            }
            path.remove(path.size() - 1);
        }
    }
}
