/**
 * Created on 5/28/16.
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that
 * amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */

public class CoinChange {

    public static void main(String[] args) {
        CoinChange app = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        int num = app.coinChange(coins, amount);
        System.out.println(num);
    }

    public int coinChange(int[] coins, int amount) {
        int[] numCoins = new int[amount+1];
        numCoins[0] = 0;
        for (int i = 1; i <= amount; ++i) numCoins[i] = -1;

        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] == i) {
                    numCoins[i] = 1;
                    break;
                }
                int index = i - coins[j];
                if (index > 0 && numCoins[index] > 0) {
                    if (numCoins[i] > 0) {
                        numCoins[i] = Math.min(numCoins[index]+1, numCoins[i]);
                    } else {
                        numCoins[i] = numCoins[index] + 1;
                    }
                }
            }
        }
        return numCoins[amount];
    }
}
