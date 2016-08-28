import java.util.Arrays;
import java.util.Random;

/**
 * (F)
 * Created on 8/28/16.
 * Given an array of integers, randomly return
 * an index of the largest number in the array.
 * For example, given array [2, 1, 2, 1, 5, 4, 5, 5],
 * return one the indexes of 4, 6, and 7 for num 5
 * with 1/3 probability.
 * O(1) space.
 */

public class RandomMaximumIndex {

    public static void main(String[] args) {
        RandomMaximumIndex app = new RandomMaximumIndex();
        int[] nums = {1, 2, 3, 3, 1, 2, 3, 1, 3};
        int[] counter = new int[nums.length];
        for (int i = 0; i < 100000; ++i) {
            int idx = app.randomMaxIndex(nums);
            ++counter[idx];
        }
        System.out.println(Arrays.toString(counter));
    }

    /**
     * The idea is similar to reservior sampling
     * by keeping only one element.
     * Scan the array and update curMax and count
     * of the curMax numbers. Generate a random
     * number from 0 to count-1 (inclusive).
     * If the random number is 0, we replace
     * the index of previously held curMax
     * with the current index.
     * @param nums
     * @return
     */
    public int randomMaxIndex(int[] nums) {
        int curMax = Integer.MIN_VALUE;
        int count = 0;
        int maxIndex = -1;
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            if (curMax < nums[i]) {
                curMax = nums[i];
                count = 1;
                maxIndex = i;
            } else if (curMax == nums[i]) {
                ++count;
                int idx = random.nextInt(count);
                if (idx == 0) {
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }
}
