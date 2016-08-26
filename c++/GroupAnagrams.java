import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 8/25/16.
 * Given an array of strings, group anagrams together.
 *
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 *
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 */

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getSortedString(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    String getSortedString(String str) {
        char[] chars = new char[26];
        for (int i = 0; i < str.length(); ++i) {
            ++chars[str.charAt(i)-'a'];
        }

        StringBuilder key = new StringBuilder(str.length());
        for (int i = 0; i < 26; ++i) {
            while (chars[i] > 0) {
                key.append(i+'a');
                --chars[i];
            }
        }
        return key.toString();
    }
}
