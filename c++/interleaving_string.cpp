/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        const int n1 = s1.size();
        const int n2 = s2.size();
        const int n3 = s3.size();
        if (n1 + n2 != n3) {
            return false;
        }

        if (n1 == 0 || n2 == 0) {
            return s1 + s2 == s3;
        }

        // isMatched[i][j] indicates whether s3.substr(0,i+j)
        // is formed by interleaving s1.substr(0,i) and s2.substr(0,j);
        bool isMatched[n1+1][n2+1];

        for (int i = 0; i < n1 + 1; ++i) {
            isMatched[i][0] = (s1.substr(0,i) == s3.substr(0, i));
        }

        for (int j = 1; j < n2 + 1; ++j) {
            isMatched[0][j] = (s2.substr(0,j) == s3.substr(0, j));
        }

        for (int i = 1; i < n1 + 1; ++i) {
            for (int j = 1; j < n2 + 1; ++j) {
                isMatched[i][j] = (isMatched[i-1][j] && (s1[i-1] == s3[i+j-1]))
                                || (isMatched[i][j-1] && (s2[j-1] == s3[i+j-1]));
            }
        }

        return isMatched[n1][n2];
    }
};

int main()
{
    string s1 = "abcd";
    string s2 = "bae";
    string s3 = "dcbbaae";
    Solution sol;
    bool ret = sol.isInterleave(s1, s2, s3);
    cout << s3 << (ret? " is" : " is not") << " an interleaved string of " << s1 << " and " << s2 << endl;
    return 0;
}