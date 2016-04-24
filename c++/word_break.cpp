/* 
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */

#include <unordered_set>
#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * @solution allocate a boolean array isBreakable[s.size()+1], each element
     * isBreakable[i] represents if s.substr(0, i+1) is breakable by wordDict.
     * Given isBreakable[i], we want to use the previous knowledge to deduce
     * isBreakable[i+1] in order to minimize dictionary search operations.
     * @param s
     * @param wordDict
     * @return 
     */
    bool wordBreak(string s, unordered_set<string>& wordDict) {
        if (s.empty()) {
            return wordDict.count(s);
        }
        
        vector<bool> isBreakable(s.size(), false);
        
        for (size_t i = 0; i < s.size(); ++i) {
            if (wordDict.count(s.substr(0, i+1))) {
                isBreakable[i] = true;
                continue;
            }
            for (size_t j = 0; j < i; ++j) {
                if (isBreakable[j] && wordDict.count(s.substr(j+1, i-j))) {
                    isBreakable[i] = true;
                    break;
                }
            }
        }

        return isBreakable.back();
    }
};

int main(int argc, char** argv) {
    unordered_set<string> wordDict;
    wordDict.insert("abc");
    wordDict.insert("a");
    wordDict.insert("b");
    wordDict.insert("cd");
    string s = "abcd";
    Solution sol;
    bool res = sol.wordBreak(s, wordDict);
    cout << s << " is " << (res? "breakable" : "unbreakable") << endl;
    return 0;
}

