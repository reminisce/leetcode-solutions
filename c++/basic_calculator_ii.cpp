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
#include <stack>
#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    /**
     * Scan the string, evaluate the expression
     * with * or / first, and save the result
     * in a stack. In the end, evaluate the sum
     * of all the numbers saved in the stack.
     */
    int calculate(const string& s) {
        if (s.empty()) return 0;
        stack<int> numStack;
        char sign = '+'; // the sign before current num
        int num = 0;
        int i = 0;
        const int len = s.size();
        for (int i = 0; i < len; ++i) {
            if (isDigit(s[i])) {
                num = num * 10 + (int)(s[i] - '0');
            }

            if ((!isDigit(s[i]) && s[i] != ' ') || len - 1 == i) {
                if (sign == '+') numStack.push(num);
                else if (sign == '-') numStack.push(-num);
                else if (sign == '*' || sign == '/') {
                    int tmp = (sign == '*'? numStack.top() * num : numStack.top() / num);
                    numStack.pop();
                    numStack.push(tmp);
                }
                sign = s[i];
                num = 0;
            }
        }

        int res = 0;
        while (!numStack.empty()) {
            res += numStack.top();
            numStack.pop();
        }
        return res;
    }

    bool isDigit(char c) const {
        return c >= '0' && c <= '9';
    }
};

int main()
{
    string s = "3 - 22 *2";
    Solution sol;
    cout << s << " = " << sol.calculate(s) << endl;
}