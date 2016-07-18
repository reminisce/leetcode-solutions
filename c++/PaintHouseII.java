/**
 * Created on 6/5/16.
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different. You have to paint
 * all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2]is the cost
 * of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */

public class PaintHouseII {
    /**
     * Define minCost[i][j] is the minimum cost of painting 0,...,i-1 houses with color j
     * for house i.
     * minCost[i][j] = costs[i][j] + min(minCost[i-1][k]) for k = 0, ..., j-1, j+1, ..., n-1
     * where n is the number of colors.
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if (null == costs || costs.length == 0 || costs[0].length == 0) return 0;
        int m = costs.length;
        int n = costs[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0) {
                    dp[i][j] = costs[i][j];
                    continue;
                }
                int preMinCost = Integer.MAX_VALUE;
                for (int k = 0; k < n; ++k) {
                    if (k == j) continue;
                    preMinCost = Math.min(dp[i-1][k], preMinCost);
                }
                dp[i][j] = costs[i][j] + preMinCost;
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < n; ++j) {
            minCost = Math.min(dp[m-1][j], minCost);
        }

        return minCost;
    }
}
