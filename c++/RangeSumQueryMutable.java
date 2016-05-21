/**
 * Created on 5/21/16.
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */


/**
 * Build a segment tree for range sums. The root is the sum of the whole array,
 * the left child of the root is the sum of the subarray from 0 to mid, and the
 * right child of the root is the sum of the subarray from mid+1 to the last element.
 * The leaves are single element value of the nums.
 * We can use an array of 2*n-1 to represent the tree.
 */

public class RangeSumQueryMutable {

    public static void main(String[] args) {
        int[] nums = {-28,-39,53,65,11,-56,-65,-39,-43,97};
        RangeSumQueryMutable rangeSumQueryMutable = new RangeSumQueryMutable(nums);
        rangeSumQueryMutable.sumRange(5,6);
        rangeSumQueryMutable.update(9,27);
        rangeSumQueryMutable.sumRange(2,3);
        rangeSumQueryMutable.sumRange(6,7);
        rangeSumQueryMutable.update(1,-82);
        rangeSumQueryMutable.update(3,-72);
        rangeSumQueryMutable.sumRange(3,7);
        rangeSumQueryMutable.sumRange(1,8);
        rangeSumQueryMutable.update(5,13);
        rangeSumQueryMutable.update(4,-67);
    }

    public RangeSumQueryMutable(int[] nums) {
        int n = nums.length;
        if (n == 0) return;
        this.nums = nums;
        int numTreeNodes = 2* (int)Math.pow(2, (int)Math.ceil(Math.log(n)/Math.log(2.0))) - 1;
        segmentTree = new int[numTreeNodes];
        buildSegmentTree();
    }

    void update(int i, int val) {
        int n = nums.length;
        if (i < 0 || i > n-1) return;
        updateRangeSum(0, 0, n-1, i, val);
        nums[i] = val;
    }

    void updateRangeSum(int index, int ss, int se, int i, int val) {
        if (i < ss || i > se) return;
        segmentTree[index] += val - nums[i];
        if (ss == se) return;
        int mid = ss + (se - ss) / 2;
        updateRangeSum(2*index+1, ss, mid, i, val);
        updateRangeSum(2*index+2, mid+1, se, i, val);
    }

    public int sumRange(int i, int j) {
        int n = nums.length;
        return queryRangeSum(0, 0, n-1, i, j);
    }

    /**
     * @param index tree node's index in the array segment tree
     * @param ss tree node index's segment start index in nums
     * @param se tree node index's segment end index in nums
     * @param i left query index of nums
     * @param j right query index of nums
     * @return
     */
    private int queryRangeSum(int index, int ss, int se, int i, int j) {
        if (i <= ss && j >= se) return segmentTree[index];
        else if (i > se || j < ss) return 0;
        else {
            int mid = ss + (se - ss) / 2;
            return queryRangeSum(2*index+1, ss, mid, i, j) + queryRangeSum(2*index+2, mid+1, se, i, j);
        }
    }

    private void buildSegmentTree() {
        if (nums == null) return;
        int n = nums.length;
        if (n == 0) return;
        buildSegmentTree(0, 0, n-1);
    }

    private void buildSegmentTree(int index, int i, int j) {
        if (i == j && i < nums.length) {
            segmentTree[index] = nums[i];
        } else if (i < j){
            int mid = i + (j - i) / 2;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            buildSegmentTree(left, i, mid);
            buildSegmentTree(right, mid+1, j);
            segmentTree[index] = segmentTree[left] + segmentTree[right];
        }
    }

    private int[] nums;
    private int[] segmentTree;
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
