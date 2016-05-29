/**
 * Created on 5/28/16.
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums.length == 0) return 0;

        int index = 0;
        int count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[index] == nums[i]) ++count;
            else --count;
            if (count == 0) {
                index = i;
                count = 1;
            }
        }
        return nums[index];
    }
}
