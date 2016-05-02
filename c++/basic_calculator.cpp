/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

/* IDEA
http://www.geeksforgeeks.org/expression-evaluation/
1. While there are still tokens to be read in,
   1.1 Get the next token.
   1.2 If the token is:
       1.2.1 A number: push it onto the value stack.
       1.2.2 A variable: get its value, and push onto the value stack.
       1.2.3 A left parenthesis: push it onto the operator stack.
       1.2.4 A right parenthesis:
         1 While the thing on top of the operator stack is not a 
           left parenthesis,
             1 Pop the operator from the operator stack.
             2 Pop the value stack twice, getting two operands.
             3 Apply the operator to the operands, in the correct order.
             4 Push the result onto the value stack.
         2 Pop the left parenthesis from the operator stack, and discard it.
       1.2.5 An operator (call it thisOp):
         1 While the operator stack is not empty, and the top thing on the
           operator stack has the same or greater precedence as thisOp,
           1 Pop the operator from the operator stack.
           2 Pop the value stack twice, getting two operands.
           3 Apply the operator to the operands, in the correct order.
           4 Push the result onto the value stack.
         2 Push thisOp onto the operator stack.
2. While the operator stack is not empty,
    1 Pop the operator from the operator stack.
    2 Pop the value stack twice, getting two operands.
    3 Apply the operator to the operands, in the correct order.
    4 Push the result onto the value stack.
3. At this point the operator stack should be empty, and the value
   stack should have only one value in it, which is the final result.
*/

#include <stack>
#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int calculate(string s) {
        if (s.empty()) return 0;

        stack<int> numStack;
        stack<char> opStack;

        int i = 0;
        while (i < s.size()) {
            if (s[i] == ' ') {
                ++i;
            } else if (s[i] == '(') {
                opStack.push('(');
                ++i;
            } else if (s[i] == ')') {
                if (opStack.top() != '(') {
                    int b = numStack.top();
                    numStack.pop();
                    int a = numStack.top();
                    numStack.pop();
                    char op = opStack.top();
                    opStack.pop(); // pop out operator
                    opStack.pop(); // pop out '('
                    int res = applyOp(a, b, op);
                    numStack.push(res);
                } else { // For example, expression is "(1)"
                    opStack.pop();
                }
                ++i;
            } else if (s[i] >= '0' && s[i] <= '9') {
                int j = i + 1;
                int num = (int)(s[i] - '0');
                while (j < s.size() && s[j] >= '0' && s[j] <= '9') {
                    num = num * 10 + (int)(s[j] - '0');
                    ++j;
                }
                numStack.push(num);
                i = j;
            } else if (s[i] == '+' || s[i] == '-') {
                while (!opStack.empty() && opStack.top() != '(') {
                    int b = numStack.top();
                    numStack.pop();
                    int a = numStack.top();
                    numStack.pop();
                    char op = opStack.top();
                    opStack.pop();
                    int res = applyOp(a, b, op);
                    numStack.push(res);
                }
                opStack.push(s[i]);
                ++i;
            }
        }

        while (!opStack.empty()) {
            int b = numStack.top();
            numStack.pop();
            int a = numStack.top();
            numStack.pop();
            char op = opStack.top();
            opStack.pop();
            int res = applyOp(a, b, op);
            numStack.push(res);
        }

        return numStack.top();
    }

    int applyOp(int a, int b, char op){
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else {
            cout << "Unsupported operator " << op << endl;
            return 0;
        }
    }
};

int main()
{
    string s = "21";
    Solution sol;
    int res = sol.calculate(s);
    cout << res << endl;
    return 0;
}