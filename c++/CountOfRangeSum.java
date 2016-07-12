import java.util.Arrays;

/**
 * Created on 7/12/16.
 * Given an integer array nums, return the
 * number of range sums that lie in
 * [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum
 * of the elements in nums between indices
 * i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n2) is trivial.
 * You MUST do better than that.
 *
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2],
 * [0, 2] and their respective sums are: -2, -1, 2.
 */

public class CountOfRangeSum {

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int lower = -2, upper = 2;
        CountOfRangeSum app = new CountOfRangeSum();
        System.out.println(app.countRangeSum(nums, lower, upper));
    }

    private static class SegmentTreeNode {
        public SegmentTreeNode(long lowerBound, long upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.val = 0;
            this.left = null;
            this.right = null;
        }
        public long lowerBound; // range sum lower bound
        public long upperBound; // range sum upper bound
        public int val; // count of sums within this range
        public SegmentTreeNode left;
        public SegmentTreeNode right;
    }

    private static class SegmentTree {

        public SegmentTree(long[] nums, int l, int r) {
            root = buildSegmentTree(nums, l, r);
        }

        private SegmentTreeNode buildSegmentTree(long[] nums, int l, int r) {
            if (null == nums || nums.length == 0 || l > r) return null;
            SegmentTreeNode node = new SegmentTreeNode(nums[l], nums[r]);
            if (l == r) return node;
            int mid = l + (r - l) / 2;
            node.left = buildSegmentTree(nums, l, mid);
            node.right = buildSegmentTree(nums, mid+1, r);
            return node;
        }

        private void update(SegmentTreeNode node, long sum) {
            if (null != node && node.lowerBound <= sum && node.upperBound >= sum) {
                ++node.val;
                update(node.left, sum);
                update(node.right, sum);
            }
        }

        private int countRangeSum(SegmentTreeNode node, long low, long high) {
            if (null == node || high < node.lowerBound || low > node.upperBound) return 0;
            if (low <= node.lowerBound && high >= node.upperBound) return node.val;
            return countRangeSum(node.left, low, high) + countRangeSum(node.right, low, high);
        }

        public void update(long sum) {
            update(root, sum);
        }

        public int countRangeSum(long low, long high) {
            return countRangeSum(root, low, high);
        }

        private SegmentTreeNode root;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (null == nums || nums.length == 0) return 0;
        long[] s = new long[nums.length];
        s[0] = nums[0];
        for (int i = 1; i < s.length; ++i) {
            s[i] = s[i-1] + nums[i];
        }
        long total = s[s.length-1];
        Arrays.sort(s);
        int lastIdx = 0;
        for (int i = 1; i < s.length; ++i) {
            if (s[i-1] != s[i]) s[++lastIdx] = s[i];
        }

        int res = 0;
        SegmentTree tree = new SegmentTree(s, 0, lastIdx);
        for (int i = nums.length-1; i >= 0; --i) {
            tree.update(total);
            total -= nums[i];
            res += tree.countRangeSum(lower+total, upper+total);
        }

        return res;
    }
}
