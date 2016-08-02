/**
* Created on 6/20/16.
* Given a string s and a dictionary of words dict,
* determine if s can be segmented into a space-separated
* sequence of one or more dictionary words.
*
* For example, given
* s = "leetcode",
* dict = ["leet", "code"].
*
* Return true because "leetcode" can be segmented as "leet code".
*/

#include <iostream>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
    bool wordBreak(string s, unordered_set<string>& wordDict) {
        const int n = s.size();
        // breakable[i] indicates whether
        // s.substr(0, i+1) is breakable
        vector<bool> breakable(n, false);
        for (int i = 0; i < s.size(); ++i) {
            if (wordDict.count(s.substr(0, i+1))) {
                breakable[i] = true;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (breakable[j] && wordDict.count(s.substr(j+1, i-j))) {
                        breakable[i] = true;
                        break;
                    }
                }
            }
        }
        return breakable[n-1];
    }
};

int main()
{
    unordered_set<string> wordDict;
    wordDict.insert("a");
    wordDict.insert("abc");
    wordDict.insert("b");
    wordDict.insert("cd");
    string s = "abcd";
    Solution sol;
    cout << sol.wordBreak(s, wordDict) << endl;
    return 0;
}
