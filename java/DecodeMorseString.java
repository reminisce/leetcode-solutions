import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 7/15/16.
 * Given a map from Morse code to char
 * and a string of Morse code, find
 * all the possible char strings it
 * can be decoded into. (G)
 */

public class DecodeMorseString {

    public static void main(String[] args) {
        Map<String, Character> morseRule = new HashMap<>();
        morseRule.put("._", 'A');
        morseRule.put(".__", 'B');
        morseRule.put("._.__", 'C');
        morseRule.put(".._", 'Z');
        String s = "._.__.._";
        DecodeMorseString app = new DecodeMorseString();
        List<String> res = app.decodeMorse(s, morseRule);
        System.out.println(res.toString());
    }

    public List<String> decodeMorse(String str, Map<String, Character> morseRule) {
        List<String> res = new ArrayList<>();
        backtrace(str, 0, morseRule, "", res);
        return res;
    }

    private void backtrace(String str, int start, Map<String, Character> morseRule, String decodedStr, List<String> res) {
        if (str.length() == start) {
            res.add(decodedStr);
            return;
        }

        for (int i = start; i < str.length(); ++i) {
            String key = str.substring(start, i+1);
            if (morseRule.containsKey(key)) {
                backtrace(str, i+1, morseRule, decodedStr+morseRule.get(key), res);
            }
        }
    }
}
