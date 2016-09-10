import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 7/21/16.
 * Given a set of candidate numbers (C)
 * and a target number (T), find all unique
 * combinations in C where the candidate
 * numbers sums to T.
 * The same repeated number may be
 * chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 * [7],
 * [2, 2, 3]
 * ]
 */

public class CombinationSum {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 3, 2, 7};
        int target = 7;
        CombinationSum app = new CombinationSum();
        List<List<Integer>> res = app.combinationSum(nums, target);
        System.out.println(res.toString());
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> nums = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumHelper(candidates, 0, target, nums, res);
        return res;
    }

    private void combinationSumHelper(int[] candidates, int start, int target,
                                      List<Integer> nums, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(nums));
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            if (i > start && candidates[i] == candidates[i-1]) continue;
            if (target >= candidates[i]) {
                nums.add(candidates[i]);
                combinationSumHelper(candidates, i, target-candidates[i], nums, res);
                nums.remove(nums.size()-1);
            }
        }
    }
}
