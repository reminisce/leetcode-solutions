import java.util.HashMap;

/**
 * Created on 7/13/16.
 * Given a string, find the first letter in the string
 * that appears only once in this string. (G)
 * Follow up:
 * 1. If the string is streamed from internet, what situations
 * do you need to handle? For example, internet shutdown.
 * 2. If the string is very long, how would you handle it?
 * Ans: MapReduce.
 */

public class FindFirstLetterInString {

    public static void main(String[] args) {
        String s = "aabbccdefeedac";
        FindFirstLetterInString app = new FindFirstLetterInString();
        System.out.println(app.getFirstLetter(s));
    }

    public char getFirstLetter(String s) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int count = 1;
            if (charCountMap.containsKey(c)) {
                count = charCountMap.get(c) + 1;
            }
            charCountMap.put(c, count);
        }

        for (int i = 0; i < s.length(); ++i) {
            if (charCountMap.get(s.charAt(i)) == 1) return s.charAt(i);
        }

        return s.charAt(0);
    }
}
