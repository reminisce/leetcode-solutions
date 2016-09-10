import java.util.HashMap;

/**
 * Created on 7/14/16.
 * Given a roman numeral, convert it to an integer.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class RomanToInteger {
    public int romanToInt(String s) {
        HashMap<Character, Integer> roman2IntMap = new HashMap<>();
        roman2IntMap.put('M', 1000);
        roman2IntMap.put('D', 500);
        roman2IntMap.put('C', 100);
        roman2IntMap.put('L', 50);
        roman2IntMap.put('X', 10);
        roman2IntMap.put('V', 5);
        roman2IntMap.put('I', 1);

        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i == s.length()-1
                    || roman2IntMap.get(s.charAt(i+1)) <= roman2IntMap.get(s.charAt(i))) {
                res += roman2IntMap.get(s.charAt(i));
            } else {
                res -= roman2IntMap.get(s.charAt(i));
            }
        }
        return res;
    }
}
