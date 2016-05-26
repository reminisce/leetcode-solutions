/**
 * Created on 5/25/16.
 * Note: This is an extension of House Robber.
 *
 * After robbing those houses on that street, the thief has found himself a new
 * place for his thievery so that he will not get too much attention. This time,
 * all houses at this place are arranged in a circle. That means the first house
 * is the neighbor of the last one. Meanwhile, the security system for these houses
 * remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each
 * house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobberII {

    public static void main(String[] args) {
        HouseRobberII app = new HouseRobberII();
        int[] nums = {1, 3, 1, 3, 100};
        int money = app.rob(nums);
        System.out.println(money);
    }

    /**
     * Since we cannot rob the first and the last houses at the same time,
     * we consider two situations respectively, and pick a greater value of them.
     * 1. Rob from the first to the second last one.
     * 2. Rob from the second to the last one.
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length-2), rob(nums, 1, nums.length-1));
    }

    private int rob(int[] nums, int start, int end) {
        if (end - start <= 1) return Math.max(nums[start], nums[end]);
        int m0 = nums[start];
        int m1 = Math.max(m0, nums[start+1]);
        int maxMoney = Math.max(m0, m1);
        for (int i = start + 2; i <= end; ++i) {
            maxMoney = Math.max(m0 + nums[i], m1);
            m0 = m1;
            m1 = maxMoney;
        }
        return maxMoney;
    }
}
