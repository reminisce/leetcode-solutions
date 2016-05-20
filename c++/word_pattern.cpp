/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
*/

#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <sstream>

using namespace std;

class Solution {
public:
    bool wordPattern(string pattern, string str) {
        unordered_map<char, int> charMap;
        unordered_map<string, int> stringMap;
        istringstream in(str);

        int i = 0;
        for (string word; in >> word; ++i) {
            auto it1 = charMap.find(pattern[i]);
            auto it2 = stringMap.find(word);
            if (it1 != charMap.end() && it2 != stringMap.end()) {
                if (it1->second != it2->second) return false;
            } else if (it1 == charMap.end() && it2 == stringMap.end()) {
                charMap[pattern[i]] = stringMap[word] = i + 1;
            } else {
                return false;
            }
        }

        return i == (int)pattern.size();
    }

    bool wordPattern2(string pattern, string str) {
        if (pattern.empty() && str.empty()) {
            return true;
        }

        if (pattern.empty() || str.empty()) {
            return false;
        }

        vector<string> words;
        splitString(str, words);
        if (words.size() != pattern.size()) {
            return false;
        }

        unordered_map<char, string> char2StringMap;
        unordered_map<string, char> string2CharMap;
        for (size_t i = 0; i < pattern.size(); ++i) {
            auto it1 = char2StringMap.find(pattern[i]);
            if (it1 == char2StringMap.end()) {
                char2StringMap[pattern[i]] = words[i];
            } else if (it1->second != words[i]) {
                return false;
            }

            auto it2 = string2CharMap.find(words[i]);
            if (it2 == string2CharMap.end()) {
                string2CharMap[words[i]] = pattern[i];
            } else if (it2->second != pattern[i]) {
                return false;
            }
        }

        return true;
    }

    void splitString(const string& str, vector<string>& words, char delimiter = ' ') {
        int i = 0;
        int len = 0;
        while (i+len <= str.size()) {
            while (i+len < str.size() && str[i+len] != delimiter) {
                ++len;
            }
            words.push_back(str.substr(i, len));
            i += len + 1;
            len = 0;
        }
    }
};

int main()
{
    string pattern = "abba";
    string str = "dog dog dog dog";
    Solution sol;
    vector<string> words;
    sol.splitString(str, words);
    for (const string& word : words) {
        cout << word << ' ';
    }
    cout << endl;
    bool ret = sol.wordPattern(pattern, str);
    cout << "String [" << str << ']' << (ret? " follows" : " does not follow") << " pattern " << pattern << endl;
    return 0;
}
