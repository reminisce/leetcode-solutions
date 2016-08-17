/**
 * Created on 8/15/16.
 * Write a function to check
 * whether a string is a number.
 * Check for all the cases lik
 * '.', '-', '+' ascii values
 * multiple occurance of symbols.
 */

public class ValidNumberSimplified {
    private static final int INVALID = 0;
    private static final int DIGIT = 1;
    private static final int DOT = 2;
    private static final int SPACE = 3;
    private static final int SIGN = 4;

    public static void main(String[] args) {
        String s = "-2. 3 ";
        ValidNumberSimplified app = new ValidNumberSimplified();
        System.out.println(s + (app.isValid(s) ? " is " : " is not ") + "a valid number");
    }

    public boolean isValid(String num) {
        int[][] transfer = {
            // INVALID  DIGIT  DOT  SPACE  SIGN
                {-1,      1,    2,    0,    3},  // 0 no input or only space so far
                {-1,      1,    4,    5,   -1},  // 1 after entering a digit
                {-1,      4,   -1,   -1,   -1},  // 2 no digits, only a dot so far
                {-1,      1,    2,   -1,   -1},  // 3 after entering a sign
                {-1,      4,   -1,    5,   -1},  // 4 has digits and a dot so far
                {-1,     -1,   -1,    5,   -1}   // 5 enter a space after entering valid digits
        };

        int state = 0;
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            if (Character.isDigit(c)) state = transfer[state][DIGIT];
            else if (c == '.') state = transfer[state][DOT];
            else if (c == ' ') state = transfer[state][SPACE];
            else if (c == '+' || c == '-') state = transfer[state][SIGN];
            else state = transfer[state][INVALID];
            if (state == -1) return false;
        }
        return state == 1 || state == 4 || state == 5;
    }
}
