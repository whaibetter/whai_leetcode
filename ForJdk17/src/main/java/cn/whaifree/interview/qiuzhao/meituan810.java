package cn.whaifree.interview.qiuzhao;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/18 15:21
 * @注释
 */
public class meituan810 {

}


// 注意类名必须为 Main, 不要有任何 package xxx 信息
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        StringBuilder sb = new StringBuilder();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            long k = in.nextInt();
            long x = in.nextInt();
            // System.out.println("n:"+n+", k:"+k+", x:"+x);
            HashSet<Integer> set = new HashSet<>();
            int[] as = new int[n];
            for (int j = 0; j < n; j++) {
                as[j] = in.nextInt();
            }
            long min = x * n;
            int cur = 0;
            for (int j = n - 1; j >= 0; j--) {
                set.add(as[j]);
                while (set.contains(cur)) {
                    cur++;
                }
                min = Math.min(x * j + k * cur, min);
                // System.out.println(set);
                // System.out.println("min:"+min+", cur:"+cur);
            }
            sb.append(min);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

/*作者：逆风_前行
    链接：https://www.nowcoder.com/exam/test/82357235/submission?examPageSource=Company&pid=58084216&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26selectStatus%3D0%26tagIds%3D179&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网*/

class p3{
    //小美有一个长度，他可以对数组进行如下操作：删除第一个元素a，同时数组的长度减一，花费为x
    // 删除整个数组，花费为k×MEX(a)（其中MEX(a)表示a 中未出现过的最小非负整数。例如[0,1,2,4][0,1,2,4] 的MEX为3
    // 小美想知道将a数组全部清空的最小代价是多少，请你帮帮他吧。

    static int allDelete;
    static int oneDelete;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numsCount = in.nextInt();
        String[] split = in.next().split(" ");
        int count = Integer.parseInt(split[0]);
        allDelete = Integer.parseInt(split[1]);
        oneDelete = Integer.parseInt(split[2]);
        for (int i = 0; i < numsCount; i++) {
            split = in.next().split(" ");
            int[] ints = new int[split.length];
            for (String s : split) {
                ints[i] = Integer.parseInt(s);
            }
        }
    }


    /**
     *               4 5 2 3 1 0
     *
     * 都删除
     * 删除第一个
     * @param ints
     */
    public static void compute(int[] ints) {
        int max = Arrays.stream(ints).max().getAsInt();
        boolean[] booleans = new boolean[max]; // 表示是否出现
        for (int i = 0; i < ints.length; i++) {
            booleans[ints[i]] = true;
        }
        int[][] dp = new int[2][ints.length];
        /**
         * dp[0] 为删除第一个元素的最小代驾
         * dp[1] 为全部删除的最小代驾
         */
        for (int i = 0; i < booleans.length; i++) {
            // 如果删除第一个元素
            dp[0][i] = oneDelete;
        }
    }
}

class ArrayCostCalculator {
    public static void main(String[] args) {
        // 4 5 2 3 1 0
        int[] array = {4, 5, 2, 3, 1, 0};
        int x = 3; // 删除第一个元素的代价
        int k = 3; // 删除整个数组的代价的系数
        int result = findMinimumCost(array, x, k);
        System.out.println("The minimum cost to clear the array is: " + result);
    }

    public static int findMinimumCost(int[] array, int x, int k) {
        int n = array.length; // 数组长度
        int mex = findMEX(array); // 计算MEX
        int costIndividualDelete = n * x; // 逐个删除的代价
        int costDeleteAll = k * mex; // 删除整个数组的代价
        return Math.min(costIndividualDelete, costDeleteAll); // 返回较小的代价
    }

    private static int findMEX(int[] array) {
        int maxElement = getMaxElement(array); // 数组中的最大元素
        boolean[] 出现过 = new boolean[maxElement + 1]; // 用于跟踪数字是否出现过

        // 标记数组中的每个元素
        for (int num : array) {
            出现过[num] = true;
        }

        // 找到MEX
        for (int i = 0; i <= maxElement; i++) {
            if (!出现过[i]) {
                return i; // 未出现的最小非负整数
            }
        }
        return maxElement + 1; // 如果数组包含所有数字，则MEX是最大元素加1
    }

    private static int getMaxElement(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

class p2{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String right = in.next();
        TreeMap<Integer, Set<String>> map = new TreeMap<>();
        // map key为长度，value为数量
        for (int i = 0; i < num; i++) {
            String next = in.next();
            int length = next.length();
            if (!map.containsKey(length)) {
                map.put(length, new HashSet<>());
            }
            map.get(length).add(next);
        }

        ArrayList<Integer> keys = new ArrayList<>();
        for (Integer i : map.keySet()) {
            if (i < right.length()) {
                keys.add(i);
            }
        }

//        List<Integer> list = map.keySet().stream().filter(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return integer < right.length();
//            }
//        }).toList();

        int pre = 0;
        for (Integer i : keys) {
            pre += map.get(i).size();
        }
        System.out.println(pre + 1 + " " + (pre + map.get(right.length()).size()));

    }
}
class p1{


    public static void main(String[] args) {
        System.out.println((int) 'z');
        System.out.println((int) 'A');


        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            String next = in.next();
            judge(next);
        }
    }


    public static void judge(String s) {
        char c = s.charAt(0);
        if (isEn(c)) {
            // 字母开头
            boolean hasNum = false;
            boolean hasEn = false;
            for (int i = 1; i < s.length(); i++) {
                boolean en = isEn(s.charAt(i));
                boolean num = isNum(s.charAt(i));
                if (!en && !num) {
                    System.out.println("invalid");
                    return;
                }
                hasEn = hasEn || en;
                hasNum = num || hasNum;
            }
            if (hasNum && !hasEn) {
                System.out.println("standard");
                return;
            } else if (!hasNum && hasEn) {
                System.out.println("invalid");
                return;
            }
            System.out.println("mix");
        } else if (isNum(c)) {
            // 数字开头
            for (int i = 1; i < s.length(); i++) {
                if (!isEn(s.charAt(i))) {
                    System.out.println("invalid");
                    return;
                }
            }
            System.out.println("special");
        }else {
            System.out.println("invalid");
        }
    }

    public static boolean isNum(char c) {
        return c <= '9' && c >= '0';
    }

    public static boolean isEn(char c) {
        return c >= 'A' && c <= 'z';
    }
}
