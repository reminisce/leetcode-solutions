import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 7/13/16.
 * Given a string, we can "shift" each of its
 * letter to its successive letter, for example:
 * "abc" -> "bcd". We can keep "shifting" which
 * forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only
 * lowercase alphabets, group all strings that
 * belong to the same shifting sequence.
 *
 * or example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 *
 * Return:
 *
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 *
 * Note: For the return value, each inner
 * list's elements must follow the lexicographic order.
 */

public class GroupShiftedStrings {

    public static void main(String[] args) {
        String[] strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        GroupShiftedStrings app = new GroupShiftedStrings();
        List<List<String>> res = app.groupStrings(strs);
        System.out.println(res.toString());
    }

    public List<List<String>> groupStrings(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            String key = "";
            for (int j = 0; j <strs[i].length(); ++j) {
                key += (strs[i].charAt(j) - strs[i].charAt(0) + 26) % 26 + "/";
            }
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(strs[i]);
        }

        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
