/**
* Created on 6/15/16.
* Given a pattern and a string str, find if str follows the same pattern.
*
* Here follow means a full match, such that there is a bijection between
* a letter in pattern and a non-empty substring in str.
*
* Examples:
*
* pattern = "abab", str = "redblueredblue" should return true.
* pattern = "aaaa", str = "asdasdasdasd" should return true.
* pattern = "aabb", str = "xyzabcxzyabc" should return false.
*
*
* Notes:
* You may assume both pattern and str contains only lowercase letters.
*/

#include <string>
#include <iostream>
#include <unordered_map>
#include <unordered_set>

using namespace std;

class Solution {
public:
    bool wordPatternMatch(const string& pattern, const string& word) {
        unordered_map<char, string> char2StringMap;
        unordered_set<string> mappedStringSet;
        return wordPatternMatchHelper(pattern, 0, word, 0, char2StringMap, mappedStringSet);
    }

    bool wordPatternMatchHelper(const string& pattern, int patternIndex,
                                const string& word, int wordIndex,
                                unordered_map<char, string>& char2StringMap,
                                unordered_set<string>& mappedStringSet) {
        if (patternIndex == (int)pattern.size() && wordIndex == (int)word.size()) return true;
        if (patternIndex == (int)pattern.size() || wordIndex == (int)word.size()) return false;

        for (int i = wordIndex; i < (int)word.size(); ++i) {
            string subWord = word.substr(wordIndex, i-wordIndex+1);
            char p = pattern[patternIndex];
            auto it = char2StringMap.find(p);
            if (it != char2StringMap.end() && it->second == subWord) {
                if (wordPatternMatchHelper(pattern, patternIndex+1, word, i+1, char2StringMap, mappedStringSet)) {
                    return true;
                }
            } else if (it == char2StringMap.end()) {
                if (mappedStringSet.count(subWord)) continue;
                char2StringMap[p] = subWord;
                mappedStringSet.insert(subWord);
                if (wordPatternMatchHelper(pattern, patternIndex+1, word, i+1, char2StringMap, mappedStringSet)) {
                    return true;
                }
                char2StringMap.erase(p);
                mappedStringSet.erase(subWord);
            }
        }

        return false;
    }
};


int main()
{
    string pattern = "abab";
    string word = "blueredbluered";
    Solution sol;
    cout << sol.wordPatternMatch(pattern, word) << endl;
    return 0;
}
