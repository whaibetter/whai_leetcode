package cn.whaifree.redo.redo_all_240924;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/14 15:26
 * @注释
 */
public class beibao01 {


    public static void main(String[] args) {

        int[] values = {2, 3, 1, 5, 4, 3};
        int[] weight = {2, 3, 1, 5, 2, 2};
        int pkgSize = 6;
        int result = bei(values, weight, pkgSize);
        System.out.println(result);
    }


    /**
     * 6 1
     * 2 2 3 1 5 2 weight
     * 2 3 1 5 4 3 value
     *
     * @param values
     * @param weight
     * @param pkgSize
     * @return
     */
    public static int bei(int[] values, int[] weight, int pkgSize) {
        int[][] dp = new int[values.length][pkgSize + 1];
        for (int i = weight[0]; i <= pkgSize; i++) {
            dp[0][i] = values[0];
        }

        for (int i = 1; i < values.length; i++) {
            for (int j = 0; j <= pkgSize; j++) {
                if (j > weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + values[i]);
                }else {
                    dp[i][j] = dp[i - 1][j]; // 放不进去
                }
            }
        }
        return dp[values.length - 1][pkgSize];
    }
}
