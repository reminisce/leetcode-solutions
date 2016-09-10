import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created on 7/19/16.
 * Given m piles of coins, each of which
 * contains coins of different values.
 * You are allowed to take n coins from
 * those piles. Maximize your gain. You
 * can only fetch coins from the top of
 * each pile. (G)
 */

public class FetchCoinsFromPiles {

    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(50);
        stack1.push(1);
        stack1.push(5);
        stack1.push(1);
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(10);
        stack2.push(15);
        stack2.push(20);
        stack2.push(1);
        Stack<Integer> stack3 = new Stack<>();
        stack3.push(10);
        stack3.push(1);
        stack3.push(5);
        stack3.push(30);

        List<Stack<Integer>> coins = new ArrayList<>();
        coins.add(stack1);
        coins.add(stack2);
        coins.add(stack3);

        int n = 4;

        FetchCoinsFromPiles app = new FetchCoinsFromPiles();
        System.out.println(app.fetchCoins(coins, n));
    }

    public int fetchCoins(List<Stack<Integer>> coinsPiles, int n) {
        int[] maxSum = new int[1];
        fetchCoinsHelper(coinsPiles, 0, n, 0, maxSum);
        return maxSum[0];
    }

    private void fetchCoinsHelper(List<Stack<Integer>> coinPiles, int pileIdx,
                                  int remainingCoins, int curSum, int[] maxSum) {
        if (remainingCoins == 0) {
            maxSum[0] = Math.max(curSum, maxSum[0]);
            return;
        }

        for (int i = pileIdx; i < coinPiles.size(); ++i) {
            Stack<Integer> curCoinPile = coinPiles.get(i);
            for (int j = 0; j <= remainingCoins; ++j) {
                int tmp = curSum;
                int count = 0;
                Stack<Integer> tmpStack = new Stack<>();
                while (!curCoinPile.isEmpty() && count < j) {
                    int topValue = curCoinPile.pop();
                    tmp += topValue;
                    tmpStack.push(topValue);
                    ++count;
                }
                fetchCoinsHelper(coinPiles, i+1, remainingCoins-count, tmp, maxSum);
                while (!tmpStack.isEmpty()) {
                    curCoinPile.push(tmpStack.pop());
                }
            }
        }
    }
}
