/**
 * Created on 8/27/16.
 * This is a follow up question of the 1D case.
 * If given a matrix, find whether there exists
 * a submatrix whose sum is equal to the target number.
 */

public class SubarraySumTarget2D {

    public static void main(String[] args) {
        int[][] nums = {{1, 5, 7}, {3, 7, -8}, {4, -8, 9}};
        int target = 0;
        SubarraySumTarget2D app = new SubarraySumTarget2D();
        System.out.println(app.hasSubMatrixEqualToTarget(nums, target));
    }

    /**
     * The idea is to create a partial sum matrix each element
     * s[i][j] represents the accumulative sum from (0, 0) to
     * (i, j). For each column pair of the partial sum matrix,
     * we can get the subarray sum of each row and save them
     * in an array. This becomes a 1D problem which can be
     * solved in linear time.
     * @param nums
     * @param target
     * @return
     */
    public boolean hasSubMatrixEqualToTarget(int[][] nums, int target) {
        int m = nums.length, n = nums[0].length;
        int[][] partialSum = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    partialSum[i][j] = nums[i][j];
                } else if (i == 0) {
                    partialSum[i][j] = partialSum[i][j-1] + nums[i][j];
                } else if (j == 0) {
                    partialSum[i][j] = partialSum[i-1][j] + nums[i][j];
                } else {
                    partialSum[i][j] = partialSum[i-1][j] + partialSum[i][j-1] - partialSum[i-1][j-1] + nums[i][j];
                }
            }
        }

        int[] partialRowSum = new int[m];
        SubarraySumTarget1D app = new SubarraySumTarget1D();
        for (int leftCol = 0; leftCol < n; ++leftCol) {
            for (int rightCol = leftCol; rightCol < n; ++rightCol) {
                for (int i = 0; i < m; ++i) {
                    partialRowSum[i] = (leftCol == 0 ? partialSum[i][rightCol]
                        : partialSum[i][rightCol] - partialSum[i][leftCol - 1]);
                    if (i > 0) {
                        partialRowSum[i] = (leftCol == 0? partialSum[i][rightCol]-partialSum[i-1][rightCol]
                        : partialSum[i][rightCol]-partialSum[i-1][rightCol]
                                -partialSum[i][leftCol-1]+partialSum[i-1][leftCol-1]);
                    }
                }
                if (app.hasSubarraySumEqualToTarget(partialRowSum, target)) return true;
            }
        }
        return false;
    }
}
