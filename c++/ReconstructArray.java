import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 7/30/16.
 * Given an array representing the number
 * of greater values on the right side of
 * an element in another array formed
 * by numbers 1, 2, 3,..., n.
 * Reconstruct the array from the greater
 * number list.
 * For example, given [0, 1, 2, 2],
 * return [4, 3, 1, 2].
 */

public class ReconstructArray {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2};
        ReconstructArray app = new ReconstructArray();
        System.out.println(Arrays.toString(app.reconstruct(nums)));
    }

    /**
     * The idea is reconstruct the array from the last element,
     * since in the beginning, we can only determine the value
     * of the last element considering nums[n-1]. Each time,
     * we remove the candidate from the candidate list, and fill
     * in the reconstructed array by removed candidate.
     * @param nums
     * @return
     */
    int[] reconstruct(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; ++i) list.add(i);

        int[] a = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            a[i] = list.remove(i-nums[i]);
        }
        return a;
    }
}
