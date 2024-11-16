package cn.whaifree.interview.js;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/10 19:27
 * @注释
 */
public class P1 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new P1().solve(new int[]{2,3,2,3,2})));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param arr int整型一维数组
     * @return int整型一维数组
     */
    public int[] solve (int[] arr) {
        // write code here

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i + 1);
        }

        int[] res = new int[arr.length];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() == 2) {
                Integer a = value.get(1);
                Integer b = value.get(0);
                res[b - 1] = a;
                res[a - 1] = b;
            }else {
                for (Integer i : value) {
                    res[i - 1] = -1;
                }
            }
        }
        return res;
    }
}

class p2{

    public static void main(String[] args) {
        System.out.println(new p2().solve(new int[]{1, 2}));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * 给你一个数组，你可以随意操作任意相邻的元素都+1，请问需要多少次可以让整个数组变成对称的
     *
     * @param arr int整型一维数组
     * @return long长整型
     */
    public long solve (int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) +
                            Math.abs(arr[i] - arr[j]);
                }
            }
        }
        int i = dp[0][n - 1];
        return i;
    }

    public static class Solution {
        public int minOperations(int[] arr) {
            int n = arr.length;

            // 如果数组长度是奇数，中心元素不参与对称操作
            int operations = 0;

            // 使用双指针，从两端往中间移动
            for (int i = 0; i < n / 2; i++) {
                int left = arr[i];
                int right = arr[n - i - 1];

                // 计算两个元素的差值
                int diff = Math.abs(left - right);

                // 如果差值是奇数，不能通过 +1 操作得到相等的数
                if (diff % 2 != 0) {
                    return -1;
                }

                // 每次需要 diff / 2 次操作来平衡这两个元素
                operations += diff / 2;
            }

            return operations;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();

            // 示例测试
            int[] arr1 = {1, 2, 2, 1, 1};
            System.out.println(solution.minOperations(arr1)); // 输出: 0

            int[] arr2 = {1, 2, 3, 4, 5};
            System.out.println(solution.minOperations(arr2)); // 输出: -1
        }
    }

}
