/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Solution {
public:
    /**
     * Scan from the last char to the first and keep
     * tracking the previous two numbers of decoding
     * substrings.
     * For example, "112",
     */
    int numDecodings(string s) {
        if (s.empty()) {
            return 0;
        }
        int d = 0, d1 =0, d2 =1;
        int i = s.size();
        while (i--) {
            if (s[i] == '0') { // The substring is not valid with zero as the first letter
                d = 0;
            } else if (stoi(s.substr(i, 2)) <= 26) { // The substring can be separated
                d = d1 + d2;
            } else { // The substring cannot be separated
                d = d2;
            }
            d1 = d2;
            d2 = d;
        }

        return d;
    }
};

int main()
{
    string str = "132";
    Solution sol;
    int num = sol.numDecodings(str);
    cout << "Number of decoding ways = " << num << endl;
    return 0;
}