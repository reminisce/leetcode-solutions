/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Hint:

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
*/

#include <string>
#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        bool negative = (numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0);
        long int num = abs((long int)numerator);
        long int den = abs((long int)denominator);
        string s = (negative? "-" : "");
        // map between remainder and quotient index in the result string
        // if the remainder is found, it means the beginning of the cycle
        unordered_map<long int, int> remainder2QuotientIdx;
        long int quotient = num / den;
        long remainder = num % den;
        s += to_string(quotient) + (remainder == 0? "" : ".");
        int pos = s.length();
        while (remainder > 0) {
            auto it = remainder2QuotientIdx.find(remainder);
            if (it != remainder2QuotientIdx.end()) {
                s.insert(it->second, "(");
                s += ")";
                break;
            }
            remainder2QuotientIdx[remainder] = pos++;
            s += to_string(remainder * 10 / den);
            remainder = (remainder * 10) % den;
        }

        return s;
    }
};

int main()
{
    int num = 11;
    int den = -30000;
    Solution sol;
    string s = sol.fractionToDecimal(num, den);
    cout << num << '/' << den << '=' << s << endl;
    return 0;
}
