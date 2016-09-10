import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 8/4/16.
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
                set.remove(num);
            }
        }

        int[] array = new int[res.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = res.get(i);
        }
        return array;
    }
}
