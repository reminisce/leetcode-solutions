import java.util.HashMap;
import java.util.Map;

/**
 * Created on 8/6/16.
 * Given two integers representing the numerator
 * and denominator of a fraction, return the
 * fraction in string format.
 *
 * If the fractional part is repeating, enclose
 * the repeating part in parentheses.
 *
 * For example,
 *
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * Hint:
 *
 * No scary math, just apply elementary math knowledge.
 * Still remember how to perform a long division?
 * Try a long division on 4/9, the repeating part is
 * obvious. Now try 4/333. Do you see a pattern?
 * Be wary of edge cases! List out as many test cases
 * as you can think of and test your code thoroughly.
 */

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        int num = 1;
        int den = 99;
        FractionToRecurringDecimal app = new FractionToRecurringDecimal();
        System.out.println(num + "/" + den + " = " + (double)num/(double)den);
        System.out.println(num + "/" + den + " = " + app.fractionToDecimal(num, den));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        boolean isNegative = (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        StringBuilder str = new StringBuilder();
        long remainder = num % den;
        long quotient = num / den;
        if (isNegative) {
            str.append('-');
        }
        str.append(quotient);
        if (remainder != 0) str.append('.');
        Map<Integer, Integer> remainder2IndexMap = new HashMap<>();
        int pos = str.length();
        while (remainder > 0) {
            if (remainder2IndexMap.containsKey((int)remainder)) {
                int index = remainder2IndexMap.get((int)remainder);
                return str.substring(0, index) + '(' + str.substring(index) + ')';
            }
            remainder2IndexMap.put((int) remainder, pos++);
            str.append(remainder*10/den);
            remainder = (remainder * 10) % den;
        }
        return str.toString();
    }
}
