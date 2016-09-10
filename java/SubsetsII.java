import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 8/25/16.
 * Given a collection of integeallSubsets that might
 * contain duplicates, nums, return all possible subsets.
 *
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 *
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */

public class SubsetsII {
    /**
     * preSize is the res size of adding a previously
     * different number from the current one. This is
     * used to avoid duplicate subsets. For example,
     * [1, 2, 2]
     * step 1: res has []
     * step 2: res has [] [1], preSize = 1, preNum = 1
     * step 3: res has [] [1] [2] [1, 2], preSize = 2, preNum = 2
     * step 4: res should be constructed from [2] [1, 2], that is from i = res.size() - preSize.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        if (nums.length == 0) return res;
        int preSize = res.size();
        int preNum = nums[0];

        for (int num : nums) {
            if (preNum != num) {
                preNum = num;
                preSize = res.size();
            }
            int sz = res.size();
            for (int i = sz - preSize; i < sz; ++i) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset);
            }
        }
        return res;
    }
}
