import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/11/16.
 * Given a string that contains only digits 0-9
 * and a target value, return all possibilities
 * to add binary operators (not unary) +, -, or
 * * between the digits so they evaluate to the
 * target value.
 *
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */

public class ExpressionAddOperators {

    public static void main(String[] args) {
        String s = "105";
        int target = 5;
        ExpressionAddOperators app = new ExpressionAddOperators();
        List<String> res = app.addOperators(s, target);
        for (String exp : res) {
            System.out.println(exp + " = " + target);
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, "", 0, 0, res);
        return res;
    }

    /**
     * @param num remaining string for adding operators
     * @param target target value
     * @param expression the current expression
     * @param curResult the current result from the current expression
     * @param prevNum the number that was added or subtracted last time
     * @param res all possible expressions
     */
    private void dfs(String num, int target, String expression, long curResult, long prevNum, List<String> res) {
        if (curResult == target && num.isEmpty()) {
            res.add(expression);
            return;
        }

        for (int i = 1; i <= num.length(); ++i) {
            String str = num.substring(0, i);
            // exclude the string starting with 0 and length > 1
            if (str.length() > 1 && str.charAt(0) == '0') {
                return;
            }

            long curNum = Long.parseLong(str);
            String nextStr = num.substring(i);
            // if not dealing with the first letter, we can add operator
            if (!expression.isEmpty()) {
                dfs(nextStr, target, expression + "*" + curNum,
                        (curResult-prevNum)+prevNum*curNum, prevNum*curNum, res);
                dfs(nextStr, target, expression + "+" + curNum, curResult+curNum, curNum, res);
                dfs(nextStr, target, expression + "-" + curNum, curResult-curNum, -curNum, res);
            } else { // the first number, cannot add operator
                dfs(nextStr, target, str, curNum, curNum, res);
            }
        }
    }
}
