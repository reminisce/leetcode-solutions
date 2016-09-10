import java.util.ArrayList;
import java.util.List;

/**
 * Created on 8/8/16.
 * Given two integers n and k,
 * return all possible combinations
 * of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; ++i) nums[i-1] = i;
        List<Integer> combo = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0) return res;
        combineHelper(nums, 0, k, combo, res);
        return res;
    }

    private void combineHelper(int[] nums, int start, int k, List<Integer> combo, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(combo));
            return;
        }

        for (int i = start; i < nums.length; ++i) {
            combo.add(nums[i]);
            combineHelper(nums, i+1, k-1, combo, res);
            combo.remove(combo.size()-1);
        }
    }
}
