import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/27/16.
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Ensure that numbers within the set are sorted in ascending order.
 *
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 *
 * Output:
 *
 * [[1,2,4]]
 *
 * Example 2:
 *
 * Input: k = 3, n = 9
 *
 * Output:
 *
 * [[1,2,6], [1,3,5], [2,3,4]]
 */

public class CombinationSumIII {

    public static void main(String[] args) {
        int k = 3, n = 9;
        List<List<Integer>> res = new CombinationSumIII().combinationSum3(k, n);
        for (List<Integer> combination : res) {
            System.out.println(combination.toString());
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> combination = new ArrayList<>();
        getCombinations(1, n, k, combination, combinations);
        return combinations;
    }

    private void getCombinations(int num, int target, int k, ArrayList<Integer> combination,
                                 List<List<Integer>> combinations) {
        if (target == 0 && combination.size() == k) {
            combinations.add(new ArrayList<>(combination));
            return;
        }

        for (int i = num; i <= 9; ++i) {
            if (target >= i && combination.size() < k) {
                combination.add(i);
                getCombinations(i+1, target-i, k, combination, combinations);
                combination.remove(combination.size()-1);
            } else {
                return;
            }
        }
    }
}
