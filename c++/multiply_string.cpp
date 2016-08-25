/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note:
The numbers can be arbitrarily large and are non-negative.
Converting the input string to integer is NOT allowed.
You should NOT use internal library such as BigInteger.
*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    string multiply(string num1, string num2) {
        int n1 = num1.size(), n2 = num2.size();
        vector<int> res(n1+n2);
        for (int i = n1-1; i >= 0; --i) {
            if (num1[i] != '0') {
                for (int j = n2-1; j >= 0; --j) {
                    res[n1+n2-i-j-2] += (num1[i]-'0') * (num2[j]-'0');
                }
            }
        }
#if 0
        for (int num : res) {
            cout << num << ' ';
        }
        cout << endl;
#endif
        int carry = 0;
        for (int i = 0; i < n1 + n2; ++i) {
            res[i] += carry;
            carry = res[i] / 10;
            res[i] %= 10;
        }

        int k = n1 + n2 - 1;
        while (k >= 0 && res[k] == 0) --k;
        if (k < 0) return "0";
        string str;
        while (k >= 0) {
            str.push_back(res[k--]+'0');
        }
        return str;
    }
};

int main()
{
    Solution sol;
    string num1 = "34";
    string num2 = "12";
    cout << num1 << " * " << num2 << " = " << stol(num1) * stol(num2) << endl;
    cout << num1 << " * " << num2 << " = " << sol.multiply(num1, num2) << endl;
    return 0;
}
