/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    string addBinary(string a, string b) {
        int i = (int)a.size() - 1;
        int j = (int)b.size() - 1;

        string str;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int sum = carry + (int)(a[i] - '0') + (int)(b[j] - '0');
            carry = sum / 2;
            str.push_back((sum%2 == 1? '1' : '0'));
            --i;
            --j;
        }

        const string& remain = (i >= 0? a : b);
        int l = (i>=0? i : j);

        while (l >= 0) {
            int sum = carry + (int)(remain[l]-'0');
            carry = sum / 2;
            str.push_back((sum%2 == 1? '1' : '0'));
            --l;
        }

        if (carry > 0) {
            str.push_back('1');
        }

        reverse(str.begin(), str.end());
        return str;
    }
};

int main()
{
    string a = "10";
    string b = "11";
    Solution sol;
    string c = sol.addBinary(a, b);
    cout << a << " + " << b << " = " << c << endl;
    return 0;
}
