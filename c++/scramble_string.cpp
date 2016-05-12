/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

#include <string>
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /**
     * The basic idea is to separate s1 into two substrings, s11 and s12,
     * and use the same separation index to separate s2 into s21 and s22.
     * Then check isScramble(s11, s21) && isScramble(s12, s22). If not, then
     * let s21 = s2.substr(0, s2.size()-index), s22 = s2.substr(s2.size()-index),
     * and check isScramble(s11, s22) && isScramble(s12, s21). If not, return false.
     * Need also to check length equalness and same char counts of two strings
     * as pre-qualification.
     */
    bool isScramble(const string& s1, const string& s2) {
        vector<int> charSet(26, 0);
        return isScramble(s1, s2, charSet);
    }

private:

    /**
     * charSet is a pre-allocated vector used to compare char set in two strings
     */
    bool isScramble(const string& s1, const string& s2, vector<int>& charSet) {
        if (s1.size() != s2.size()) return false;
        if (s1 == s2) return true;
        if (!isSameChars(s1, s2, charSet)) return false;

        for (size_t i = 1; i < s1.size(); ++i) {
            string s11 = s1.substr(0, i);
            string s12 = s1.substr(i);
            string s21 = s2.substr(0, i);
            string s22 = s2.substr(i);
            if (isScramble(s11, s21, charSet) && isScramble(s12, s22, charSet)) return true;

            s21 = s2.substr(0, s2.size()-i);
            s22 = s2.substr(s2.size()-i);
            if (isScramble(s11, s22, charSet) && isScramble(s12, s21, charSet)) return true;
        }

        return false;
    }

    bool isSameChars(const string& s1, const string& s2, vector<int>& charSet) {
        for (size_t i = 0; i < s1.size(); ++i) {
            ++charSet[(int)(s1[i]-'a')];
            --charSet[(int)(s2[i]-'a')];
        }
        for (int v : charSet) {
            if (v != 0) {
                resetCharSet(charSet);
                return false;
            }
        }
        return true;
    }

    void resetCharSet(vector<int>& charSet) {
        for (int& v : charSet) {
            v = 0;
        }
    }
};

int main()
{
    string s1 = "ab";
    string s2 = "ba";
    Solution sol;
    bool ret = sol.isScramble(s1, s2);
    cout << s2 << (ret? " is" : " is not") << " a scrambled string of " << s1 << endl;
    return 0;
}