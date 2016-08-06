/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

#include <string>

class Solution {
public:
    /**
     * Use greedy algorithm, try to match every char of p with s.
     * If current char is '*' in p, store its position in star
     * and the current char of s in mark. Try to mark substr of p
     * after this char with s starting from mark. If no match,
     * advance one char in s and try to match the substr of p
     * after star and repeate until success.
     * Remember to clean up the '*' in the tail of p.
     */
    bool isMatch(string s, string p) {
        int i = 0;
        int j = 0;
        int star = -1; // position of the previous star in p
        int mark = -1; // position of the char in i that matches with substr of p after previous *

        while (i < s.size()) {
            if (j < p.size() && (p[j] == '?' || p[j] == s[i])) {
                ++i;
                ++j;
            } else if (j < p.size() && p[j] == '*') {
                star = j;
                ++j;
                mark = i;
            } else if (star != -1) { // rematch
                i = ++mark;
                j = star + 1;
            } else {
                return false;
            }
        }

        while (j < p.size() && p[j] == '*') ++j;
        return j == p.size();
    }
};