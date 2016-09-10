import java.util.Stack;

/**
 * Created on 7/6/16.
 * Implement a basic calculator to
 * evaluate a simple expression string.
 *
 * The expression string contains only non-negative
 * integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */

public class BasicCalculatorII {

    public static void main(String[] args) {
        BasicCalculatorII app = new BasicCalculatorII();
        String s = "3 + 2* 2 - 4 / 5 + 7 / 3 * 2  ";
        System.out.println(s + " = " + app.calculate(s));
    }

    /**
     * IDEA
     * http://www.geeksforgeeks.org/expression-evaluation/
     * 1. While there are still tokens to be read in,
     *   1.1 Get the next token.
     *   1.2 If the token is:
     *     1.2.1 A number: push it onto the value stack.
     *     1.2.2 A variable: get its value, and push onto the value stack.
     *     1.2.3 A left parenthesis: push it onto the operator stack.
     *     1.2.4 A right parenthesis:
     *       1 While the thing on top of the operator stack is not a
     *         left parenthesis,
     *         1 Pop the operator from the operator stack.
     *         2 Pop the value stack twice, getting two operands.
     *         3 Apply the operator to the operands, in the correct order.
     *         4 Push the result onto the value stack.
     *       2 Pop the left parenthesis from the operator stack, and discard it.
     *     1.2.5 An operator (call it thisOp):
     *       1 While the operator stack is not empty, and the top thing on the
     *         operator stack has the same or greater precedence as thisOp,
     *         1 Pop the operator from the operator stack.
     *         2 Pop the value stack twice, getting two operands.
     *         3 Apply the operator to the operands, in the correct order.
     *         4 Push the result onto the value stack.
     *       2 Push thisOp onto the operator stack.
     * 2. While the operator stack is not empty,
     *   1 Pop the operator from the operator stack.
     *   2 Pop the value stack twice, getting two operands.
     *   3 Apply the operator to the operands, in the correct order.
     *   4 Push the result onto the value stack.
     * 3. At this point the operator stack should be empty, and the value
     *    stack should have only one value in it, which is the final result.
     */
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') ++i;
            if (i == s.length()) break;
            if (s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == '*' || s.charAt(i) == '/') {
                while (!opStack.isEmpty() && hasEqualOrGreaterPrecedence(opStack.peek(), s.charAt(i))) {
                    int b = numStack.pop();
                    int a = numStack.pop();
                    char op = opStack.pop();
                    int num = applyOp(a, b, op);
                    numStack.push(num);
                }
                opStack.push(s.charAt(i++));
            } else {
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') ++j;
                int num = Integer.parseInt(s.substring(i, j));
                numStack.push(num);
                i = j;
            }
        }

        while (!opStack.isEmpty()) {
            int b = numStack.pop();
            int a = numStack.pop();
            int num = applyOp(a, b, opStack.pop());
            numStack.push(num);
        }

        return numStack.peek();
    }

    private int applyOp(int a, int b, char op) {
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else if (op == '*') return a * b;
        else if (op == '/') return a / b;

        return 0;
    }

    /**
     * Determine whether op1 has equal or greater precedence than op2
     * @param op1
     * @param op2
     * @return
     */
    private boolean hasEqualOrGreaterPrecedence(char op1, char op2) {
        if (op1 == '*' || op1 == '/') return true;
        else if (op2 == '*' || op2 == '/') return false;
        else return true;
    }
}
