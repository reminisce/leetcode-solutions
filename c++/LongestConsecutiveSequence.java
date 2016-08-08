import java.util.*;

/**
 * Created on 8/7/16.
 * Given an unsorted array of integers,
 * find the length of the longest consecutive
 * elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is
 * [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 */

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence app = new LongestConsecutiveSequence();
        System.out.println(app.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLen = 1;
        while(!numSet.isEmpty()) {
            int seed = numSet.iterator().next();
            numSet.remove(seed);

            // find smaller numbers
            int len = 1;
            int num = seed - 1;
            while (numSet.contains(num)) {
                ++len;
                numSet.remove(num--);
            }

            // find greater numbers
            num = seed + 1;
            while (numSet.contains(num)) {
                ++len;
                numSet.remove(num++);
            }
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}
