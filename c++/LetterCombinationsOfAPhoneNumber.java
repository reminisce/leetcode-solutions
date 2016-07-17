import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created on 5/27/16.
 * Given a digit string, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 *
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber app = new LetterCombinationsOfAPhoneNumber();
        List<String> res = app.letterCombinationsIterative("123");
        for (String str : res) {
            System.out.println(str);
        }
    }

    public List<String> letterCombinations(String digits) {
        ArrayList<String> letterCombos = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return letterCombos;
        String[] num2StringMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        getLetterCombinations(digits, 0, num2StringMap, "", letterCombos);
        return letterCombos;
    }

    private void getLetterCombinations(String digits, int digitIdx,
                                       String[] num2StringMap, String oneCombo,
                                       ArrayList<String> letterCombos) {
        if (digitIdx == digits.length()) {
            letterCombos.add(oneCombo);
            return;
        }

        String str = num2StringMap[digits.charAt(digitIdx)-'0'];
        if (str.isEmpty()) {
            getLetterCombinations(digits, digitIdx+1, num2StringMap, oneCombo, letterCombos);
        } else {
            for (int i = 0; i < str.length(); ++i) {
                getLetterCombinations(digits, digitIdx + 1, num2StringMap, oneCombo + str.charAt(i), letterCombos);
            }
        }
    }

    public List<String> letterCombinationsIterative(String digits) {
        String[] num2StringMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> list = new LinkedList<>();
        if (digits.isEmpty()) return list;
        list.add("");
        for (int i = 0; i < digits.length(); ++i) {
            int n = list.size();
            String str = num2StringMap[digits.charAt(i)-'0'];
            if (str.isEmpty()) continue;
            for (int j = 0; j < n; ++j) {
                String tmp = list.pollFirst();
                for (int k = 0; k < str.length(); ++k) {
                    list.add(tmp + str.charAt(k));
                }
            }
        }
        return list;
    }
}
