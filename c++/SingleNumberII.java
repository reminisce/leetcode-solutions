/**
 * Created on 7/22/16.
 * Given an array of integers, every element
 * appears three times except for one. Find
 * that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 */

public class SingleNumberII {

    int singleNumber(int[] nums) {
        int[] count = new int[32];
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            for (int num : nums) {
                if (((num >> i) & 1) > 0) count[i] = (count[i] + 1) % 3;
            }
            res |= count[i] << i;
        }
        return res;
    }
}
