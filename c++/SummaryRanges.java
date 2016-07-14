import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/14/16.
 * Given a sorted integer array without
 * duplicates, return the summary of its ranges.
 *
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class SummaryRanges {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        SummaryRanges app = new SummaryRanges();
        List<String> res = app.summaryRanges(nums);
        System.out.println(res.toString());
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (null == nums || nums.length == 0) return res;
        int i = 0;
        while (i < nums.length) {
            int j = i;
            while (j < nums.length-1 && nums[j] + 1 == nums[j+1]) {
                ++j;
            }
            res.add(i == j? Integer.toString(nums[i]) : nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return res;
    }
}
