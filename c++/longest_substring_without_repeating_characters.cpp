/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

#include <string>
#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Use a sliding window and a hashmap to record
     * the index of each character in the window.
     * If one character comes into the window and can
     * be found in the hashmap, we replace the index
     * value in the hashmap with the latest index
     * and move window's left index to there. In this process,
     * we keep tracking the longest substring.
     */
    int lengthOfLongestSubstring(string s) {      
        if (s.empty()) return 0;
        unordered_map<char, int> charMap;
        int len = 1;
        charMap[s[0]] = 0;
        for (int start = 0, end = 1; end < s.size(); ++end) {
            auto it = charMap.find(s[end]);
            if (it != charMap.end()) {
                int index = start;
                start = it->second + 1;
                while (index < it->second) {
                    charMap.erase(s[index++]);
                }
                it->second = end;
            } else {
                charMap[s[end]] = end;
            }
            len = max(len, end - start + 1);
        }

        return len;
    }
};

int main()
{
    string s = "abba";
    Solution sol;
    int len = sol.lengthOfLongestSubstring(s);
    cout << "len = " << len << endl;
    return 0;
}