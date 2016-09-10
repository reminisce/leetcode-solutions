import java.util.Arrays;

/**
 * (F)
 * Created on 8/28/16.
 * Given a sorted array, find the longest arithmetic progression.
 */
public class LongestArithmeticProgression {

    public static void main(String[] args) {
        //            0  1  2  3  4  5  6   7   8   9   10  11  12
        int[] nums = {3, 4, 5, 7, 8, 9, 11, 13, 14, 15, 16, 17, 18};
        LongestArithmeticProgression app = new LongestArithmeticProgression();
        System.out.println(Arrays.toString(app.longestArith(nums)));
    }

    /**
     * Use an matrix length, where length[i][j]
     * denotes the longest arithmetic progression
     * subsequence with nums[i] and nums[j] as the first
     * two elements, i < j.
     * Then for each fixed nums[k], k = 1,...,n-2, let
     * i = k-1, j = k+1, find i and j such that
     * nums[i]+nums[j] = 2*nums[k].
     * The last column of length is all 2.
     * @param nums
     * @return
     */
    public int[] longestArith(int[] nums) {
        int n = nums.length;
        int[][] length = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                length[i][j] = 2;
            }
        }

        // start k = n-2, ..., 1
        // fill up the length matrix
        // from bottom right to top left.
        int maxLength = 2;
        int maxInc = 0; // the difference of the LLAP
        int firstNum = 0; // last num in the LLAP

        for (int k = n-2; k >= 1; --k) {
            int i = k - 1;
            int j = k + 1;
            while (i >= 0 && j < n) {
                if (nums[i] + nums[j] > 2 * nums[k]) {
                    --i;
                } else if (nums[i] + nums[j] < 2 * nums[k]) {
                    ++j;
                } else {
                    length[i][k] = length[k][j] + 1;

                    if (length[i][k] > maxLength) {
                        maxLength = length[i][k];
                        maxInc = nums[k] - nums[i];
                        firstNum = nums[i];
                    }
                    --i;
                    ++j;
                }
            }
        }

        print(length);

        int[] ap = new int[maxLength];
        ap[0] = firstNum;
        for (int i = 1; i < ap.length; ++i) {
            ap[i] = ap[i-1] + maxInc;
        }

        return ap;
    }

    private void print(int[][] length) {
        for (int[] r : length) {
            System.out.println(Arrays.toString(r));
        }
    }
}
