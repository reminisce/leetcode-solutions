import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/27/16.
 * Given an integer array of size n, find all elements that appear more than
 * ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 *
 * Hint:
 *
 * How many majority elements could it possibly have?
 */

public class MajorityElementII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 3, 4, 2, 2, 1, 2, 2};
        MajorityElementII app = new MajorityElementII();
        List<Integer> res = app.majorityElement(nums);
        System.out.println("Majority elements are " + res.toString());
    }

    /**
     * There can only be at most two elements appearing more than n/3 times
     * in the array. Similar to Majority Element I, first find out two
     * possible majority numbers, and then scan the array again and count
     * their frequencies.
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> majorityElements = new ArrayList<>();
        int idx1 = -1, idx2 = -1; // majority element indices
        int frequency1 = 0, frequency2 = 0; // majority element frequencies;

        // choose two majority elements
        for (int i = 0; i < nums.length; ++i) {
            if (idx1 >= 0 && nums[i] == nums[idx1]) {
                ++frequency1;
            } else if (idx2 >= 0 && nums[i] == nums[idx2]) {
                ++frequency2;
            } else if (idx1 < 0 || frequency1 == 0) {
                idx1 = i;
                frequency1 = 1;
            } else if (idx2 < 0 || frequency2 == 0) {
                idx2 = i;
                frequency2 = 1;
            } else {
                --frequency1;
                --frequency2;
            }
        }

        frequency1 = 0;
        frequency2 = 0;
        for (int num : nums) {
            if (idx1 >= 0 && nums[idx1] == num) ++frequency1;
            else if (idx2 >= 0 && nums[idx2] == num) ++frequency2;
        }

        if (frequency1 > nums.length/3) {
            majorityElements.add(nums[idx1]);
        }
        if (frequency2 > nums.length/3) {
            majorityElements.add(nums[idx2]);
        }

        return majorityElements;
    }
}
