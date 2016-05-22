import java.util.Stack;

/**
 * Created on 5/21/16.
 * Google Phone Interview
 * Given a string "abbaba4x[a]bb3x[abaa2x[bab]]", decompress it into
 * "abbabaaaaabbabbababbababbababbababbababbab"
 */

public class StringDecompressor {

    public static void main(String[] args) {
        String str = "abbaba4x[a]bb3x[abaa2x[bab]]";
        String decompressedStr = new StringDecompressor().decompress(str);
        String expectedDecompressedStr = "abbabaaaaabbabaababbababaababbababaababbab";
        System.out.println("Input string is " + str);
        System.out.println("Decompressed string is " + decompressedStr);
        System.out.println("Decompressed string is"
                          + (decompressedStr.equals(expectedDecompressedStr)? " equal" : " not equal")
                          + " to the expected decompressed string");
    }

    public String decompress(String str) {
        Stack<Character> characterStack = new Stack<>();
        Stack<Integer> integerStack = new Stack<>();

        for (int i = 0; i < str.length();) {
            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || str.charAt(i) == '[') {
                characterStack.push(str.charAt(i));
                ++i;
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int j = i + 1;
                while (j < str.length() && str.charAt(j) != 'x') {
                    ++j;
                }
                integerStack.push(Integer.parseInt(str.substring(i, j)));
                i = j + 1;
            } else if (str.charAt(i) == ']') {
                StringBuilder repeatedStr = new StringBuilder();
                while (characterStack.peek() != '[') {
                    repeatedStr.append(characterStack.pop());
                }
                characterStack.pop(); // pop out '['
                int repeatedTimes = integerStack.pop();
                while (repeatedTimes-- > 0) {
                    for (int k = repeatedStr.length()-1; k >= 0; --k) {
                        characterStack.push(repeatedStr.charAt(k));
                    }
                }
                ++i;
            } else {
                System.out.println("ERROR: Invalid character str[" + i + "]=" + str.charAt(i));
                return "";
            }
        }

        StringBuilder decompressedStr = new StringBuilder();
        while (!characterStack.isEmpty()) {
            decompressedStr.append(characterStack.pop());
        }

        return decompressedStr.reverse().toString();
    }
}
