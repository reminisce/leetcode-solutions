/*
Given a string which contains only lowercase letters,
remove duplicate letters so that every letter appear
once and only once. You must make sure your result
is the smallest in lexicographical order among all
possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Count the frequency of letters in the string.
     * Loop through the string, each time,
     * 1. if the current string is already in the result string, continue
     * 2. if the current string is not used, compare it with the back
     * of the result string. If smaller and the back char has remaining
     * frequency, it means we can pop back char to make the result string
     * lexically smaller. Keep doing this until either the result string
     * is empty or the current char is greater than the back char.
     */
    string removeDuplicateLetters(string s) {
        int frequency[26] = {0};
        bool visited[26] = {false};
        string str;
        for (auto c : s) ++frequency[(int)(c-'a')];
        for (auto c : s) {
            int idx = (int)(c - 'a');
            --frequency[idx];
            if (visited[idx]) continue;
            while (!str.empty() && c < str.back() && frequency[(int)(str.back()-'a')] > 0) {
                visited[(int)(str.back()-'a')] = false;
                str.pop_back();
            }
            str.push_back(c);
            visited[idx] = true;
        }
        return str;
    }
};
