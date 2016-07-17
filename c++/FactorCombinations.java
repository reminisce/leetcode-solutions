import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/16/16.
 * Numbers can be regarded as product
 * of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 *
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 *
 *
 * Examples:
 * input: 1
 * output:
 *
 * []
 * input: 37
 *
 * output:
 *
 * []
 * input: 12
 * output:
 *
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * input: 32
 * output:
 *
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 */

public class FactorCombinations {

    public static void main(String[] args) {
        FactorCombinations app = new FactorCombinations();
        int n = 12;
        System.out.println(app.getFactors(n).toString());
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        getFactorsHelper(n, n, 2, combo, res);
        return res;
    }

    private void getFactorsHelper(int originalNum, int currrentNum,
                                  int start, List<Integer> combo, List<List<Integer>> res) {
        if (currrentNum <= 1) {
            res.add(new ArrayList<>(combo));
            return;
        }

        for (int i = start; i <= currrentNum && i < originalNum; ++i) {
            if (currrentNum % i == 0) {
                combo.add(i);
                getFactorsHelper(originalNum, currrentNum/i, i, combo, res);
                combo.remove(combo.size()-1);
            }
        }
    }
}
