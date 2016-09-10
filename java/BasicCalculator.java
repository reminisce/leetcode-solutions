import java.util.Stack;

/**
 * Created on 7/14/16.
 * Implement a basic calculator to evaluate
 * a simple expression string.
 *
 * The expression string may contain open
 * ( and closing parentheses ), the plus + or
 * minus sign -, non-negative integers and
 * empty spaces .
 *
 * You may assume that the given expression
 * is always valid.
 *
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 */

public class BasicCalculator {

    public static void main(String[] args) {
        String s = "(9568+(9040-(380+(2042-(7115)+(6294)-(4395-(5183+9744+" +
                "(7746-(1099+2718))-(9370-(8561+(9302)-(7632+(8451-(1759+(7760))" +
                "-(3377+5363+9093+(8332-(4492-(1151+(1165-8833+(775+(3749)+9399))" +
                "+9112+(6273+(7285-(6112-(668-(7756-4316-(582+1835-(6644+690+1204" +
                "-(7197+(7897))+(7009-(7262))-7782-(7858+(7644+(9461+(2224)-(7531" +
                "-1095-(891+1022)+2197-(9855)))+(6663-(7417-(6158-(3610))+(1481))" +
                "-(4182+(4761)))+(5017))+(9990)+(6218)))-(2904)+(5631)-(8888)+3401" +
                "+(3569))+(1135))-(3695-(7713+(3479)-(9813+(8171+(8616-8026+(4634" +
                "-(6973))-(9761-(623-4782)+(2514)+(6233)))))+(6140))-(6641)-8611" +
                "+(8389)+8074-(4412))-(3703)+(9688+(9513))))-(4987)))+(9647)))))))))-(2299))-(4785))))))";
        BasicCalculator app = new BasicCalculator();
        System.out.println(s + " = " + app.calculate(s));
    }

    public int calculate(String s) {
        int i = 0, sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < s.length()) {
            // Case number: add it to res with correct sign
            // where sign is the operator before it
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = num * 10 + (s.charAt(j) - '0');
                    ++j;
                }
                i = j;
                res += num * sign;
            }
            // Case '+': switch sign to 1
            else if (s.charAt(i) == '+') {
                sign = 1;
                ++i;
            }
            // Case '-': switch sign to -1
            else if (s.charAt(i) == '-') {
                sign = -1;
                ++i;
            }
            // Case '(': for example
            // 3    -   ( 4 + 5 )
            // ^    ^   ^
            // res sign i
            // We put res and sign into stack
            // and reset res = 0, sign = 1.
            // After the expression in the parenthesis
            // is calculated, we pop out sign and res
            // and do res_in_stack + sign_in_stack * res_in_parenthesis
            else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                ++i;
            }
            // Case ')'
            else if (s.charAt(i) == ')') {
                res = stack.pop() * res + stack.pop();
                ++i;
            }
            else { // s.charAt(i) = ' '
                ++i;
            }
        }
        return res;
    }
}
