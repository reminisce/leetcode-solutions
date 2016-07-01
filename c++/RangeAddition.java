import java.util.Arrays;

/**
 * Created on 7/1/16.
 * Assume you have an array of length n initialized
 * with all 0's and are given k update operations.
 *
 * Each operation is represented as a triplet:
 * [startIndex, endIndex, inc] which increments each
 * element of subarray A[startIndex ... endIndex]
 * (startIndex and endIndex inclusive) with inc.
 *
 * Return the modified array after all k operations were executed.
 *
 * Example:
 *
 * Given:
 *
 * length = 5,
 * updates = [
 * [1,  3,  2],
 * [2,  4,  3],
 * [0,  2, -2]
 * ]
 *
 * Output:
 *
 * [-2, 0, 3, 5, 3]
 * Explanation:
 *
 * Initial state:
 * [ 0, 0, 0, 0, 0 ]
 *
 * After applying operation [1, 3, 2]:
 * [ 0, 2, 2, 2, 0 ]
 *
 * After applying operation [2, 4, 3]:
 * [ 0, 2, 5, 5, 3 ]
 *
 * After applying operation [0, 2, -2]:
 * [-2, 0, 3, 5, 3 ]
 * Hint:
 *
 * Thinking of using advanced data structures? You
 * are thinking it too complicated.
 * For each update operation, do you really need to
 * update all elements between i and j?
 * Update only the first and end element is sufficient.
 * The optimal time complexity is O(k + n) and uses
 * O(1) extra space.
 */

public class RangeAddition {

    public static void main(String[] args) {
        RangeAddition app = new RangeAddition();
        int length = 5;
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        int[] nums = app.getModifiedArray(length, updates);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * For each update, only need to add update[2] to nums[update[0]]
     * and subtract update[2] from nums[update[1]+1].
     * After looping through all the updates, loop through nums,
     * and add nums[i-1] to nums[i].
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        for (int[] update : updates) {
            nums[update[0]] += update[2];
            if (update[1] < length-1) {
                nums[update[1]+1] -= update[2];
            }
        }

        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
