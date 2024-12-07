package cn.whaifree.redo.redo_all_241121;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 15:37
 * @注释
 */
public class MultiPkg {
    /**
     *
     *
     * @param weights 重量
     * @param values  价值
     * @param sizes   物品数量
     */
    public int maxProfit(int[] weights, int[] values, int[] sizes, int pkgSize) {
        int[] dp = new int[pkgSize + 1];
        int n = weights.length;

        for (int i = 0; i < n; i++) {
            // 物品
            int compoCount = sizes[i];
            for (int j = pkgSize; j >= 0; j--) {
                if (j >= weights[i]) {
//                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                    // 遍历所有物品的数量
                    for (int k = 1; k <= compoCount; k++) {
                        if (k * weights[i] <= j) {
                            dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return dp[pkgSize];
    }
}
