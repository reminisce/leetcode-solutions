import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/5/16.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * Hint:
 *
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */

public class StrobogrammaticNumberII {

    public static void main(String[] args) {
        StrobogrammaticNumberII app = new StrobogrammaticNumberII();
        List<String> res = app.findStrobogrammatic(5);
        for (String str : res) {
            System.out.println(str);
        }
    }

    public List<String> findStrobogrammatic(int n) {
        char[] nums = {'0', '1', '6', '8', '9'};
        List<String> res = new ArrayList<>();
        findStrobogrammatic(0, n, nums, "", res);
        return res;
    }

    /**
     * Build the string of the left half (with mid char if n is odd) recursively.
     * [Similar problem: Given 01*1*, where * can be 0 or 1, find all the strings
     * that match this pattern.]
     * Then reverse the left half (excluding the mid char) and concatenate
     * the left half with the reversed string.
     * @param start
     * @param n
     * @param nums
     * @param str
     * @param res
     */
    private void findStrobogrammatic(int start, int n, char[] nums, String str, List<String> res) {
        int halfLength = (n % 2 == 0? n/2 : n/2 + 1);
        if (str.length() == halfLength) {
            int lastIdx = (n%2 == 1? str.length()-2 : str.length()-1);
            StringBuilder s = new StringBuilder(str);
            for (int i = lastIdx; i >= 0; --i) {
                if (str.charAt(i) == '6') {
                    s.append('9');
                } else if (str.charAt(i) == '9') {
                    s.append('6');
                } else {
                    s.append(str.charAt(i));
                }
            }
            res.add(s.toString());
            return;
        }

        for (int i = start; i < halfLength; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                // The leftmost digit cannot be 0 if n > 1.
                if (i == 0 && nums[j] == '0' && n > 1) continue;
                // The mid digit cannot be 6 or 9.
                if (n%2 == 1 && i == n/2 && (nums[j] == '6' || nums[j] == '9')) continue;
                findStrobogrammatic(i+1, n, nums, str + nums[j], res);
            }
        }
    }
}
