/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

public class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * Define buy[i] and sell[i] as the max profit you can get from ending with a buy before day i (inclusive),
     * and with sell before day i (inclusive).
     * For buy[i], the max profit may either come from taking a rest on day i and ending with a buy
     * before i-1 (inclusive), or buy at i, but with a ending sell before i-2 inclusive, that is,
     * buy[i] = max(buy[i-1], sell[i-2]-prices[i]). (Eq. 1)
     * For sell[i], the max profit may either come from an ending sell before day i-1 (inclusive), or
     * a buy before i-1 (inclusive) and sell on day i.
     * sell[i] = max(sell[i-1], buy[i-1]+prices[i]). (Eq. 2)
     * To reduce the space complexity, we re-write (Eqs. 1 and 2)
     * b2 = max(b1, s0 - prices[i]),
     * s2 = max(s1, b1 + prices[i]),
     * by denoting b2 = buy[i], b1 = buy[i-1], s0 = sell[i-2], s2 = sell[i], and s1 = sell[i-1].
     * The final max profit is sell[n-1] of course.
     * We don't need to define rest[i] because buy[i] <= rest[i] = sell[i-1] <= sell[i].
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int b0 = -prices[0]; // buy on 0-th day
        int s0 = 0; // sell on 0-th day
        int s1 = Math.max(0, b0 + prices[1]); // sell on or before day 1
        int b1 = Math.max(b0, -prices[1]); // buy on or before day 1

        for (int i = 2; i < prices.length; ++i) {
            int b2 = Math.max(b1, s0 - prices[i]);
            int s2 = Math.max(s1, b1 + prices[i]);

            s0 = s1;
            s1 = s2;
            b1 = b2;
        }

        return s1;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        BestTimeToBuyAndSellStockWithCooldown app = new BestTimeToBuyAndSellStockWithCooldown();
        int profit = app.maxProfit(prices);
        System.out.println("Max profit is " + profit + "\n");
    }
}
