import java.util.*;

/**
 * Created on 7/24/16.
 * Given an array, find out the elements
 * whose frequencies are more than n/k. (G)
 */

public class FindKPopularElements {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 3;
        FindKPopularElements app = new FindKPopularElements();
        List<Integer> res = app.getPopularElements(nums, k);
        System.out.println(res.toString());
    }
    /**
     * Find the elements with more than n/k frequencies in nums.
     * There can only be k-1 elements with more than n/k frequencies
     * at most.
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> getPopularElements(int[] nums, int k) {
        Map<Integer, Integer> num2FreqMap = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            if (num2FreqMap.containsKey(nums[i])) {
                num2FreqMap.put(nums[i], num2FreqMap.get(nums[i])+1);
            } else if (num2FreqMap.size() < k - 1) {
                num2FreqMap.put(nums[i], 1);
            } else {
                Iterator<Map.Entry<Integer, Integer>> iter = num2FreqMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue() == 1) {
                        iter.remove();
                    } else {
                        entry.setValue(entry.getValue()-1);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : num2FreqMap.entrySet()) {
            num2FreqMap.put(entry.getKey(), 0);
        }

        for (int num : nums) {
            if (num2FreqMap.containsKey(num)) {
                num2FreqMap.put(num, num2FreqMap.get(num)+1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : num2FreqMap.entrySet()) {
            if (entry.getValue() > nums.length / k) {
                res.add(entry.getKey());
            }
        }

        return res;
    }
}
