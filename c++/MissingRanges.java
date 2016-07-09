import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/9/16.
 * Given a sorted integer array where the
 * range of elements are [0, 99] inclusive,
 * return its missing ranges.
 * For example, given [0, 1, 3, 50, 75],
 * return [“2”, “4->49”, “51->74”, “76->99”]
 */

public class MissingRanges {

    public static void main(String[] args) {
        int[] nums = {3, 50, 75, 98};
        int lower = 0, upper = 99;
        MissingRanges app = new MissingRanges();
        List<String> res = app.findMissingRanges(nums, lower, upper);
        System.out.println(res.toString());
    }

    List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> missingRanges = new ArrayList<>();
        if (null == nums || nums.length == 0) return missingRanges;
        int n = nums.length;
        if (nums[0] > lower) {
            missingRanges.add(nums[0]-lower == 1? "" + lower : lower + "->" + (nums[0]-1));
        }
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] + 1 == nums[i+1]) {
                continue;
            }
            missingRanges.add(nums[i+1]-nums[i] == 2? "" + (nums[i]+1) : ((nums[i]+1) + "->" + (nums[i+1]-1)));
        }
        if (nums[n-1] < upper) {
            missingRanges.add(upper-nums[n-1] == 1? "" + upper : (nums[n-1]+1) + "->" + upper);
        }
        return missingRanges;
    }
}
