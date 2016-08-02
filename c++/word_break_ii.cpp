/*
Given a string s and a dictionary of words dict, add
spaces in s to construct a sentence where each word
is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

#include <iostream>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
    vector<string> wordBreak(string s, unordered_set<string>& wordDict) {
        vector<string> res;
        vector<bool> breakable(s.size(), true);
        wordBreakHelper(s, wordDict, 0, breakable, "", res);
        return res;
    }

    void wordBreakHelper(const string& s, const unordered_set<string>& wordDict, int start,
                         vector<bool>& breakable, string sentence, vector<string>& res) {
        if (start == s.size()) {
            res.push_back(sentence);
            return;
        }

        for (int i = start; i < s.size(); ++i) {
            string str = s.substr(start, i-start+1);
            if (wordDict.count(str) && (i+1 == s.size() || breakable[i+1])) {
                int sz = res.size();
                wordBreakHelper(s, wordDict, i+1, breakable, sentence+str+(i+1==s.size()? "" : " "), res);
                if (res.size() == sz) breakable[i+1] = false;
            }
        }
    }
};

int main()
{
    string s = "catsanddogs";
    unordered_set<string> dict;
    dict.insert("cats");
    dict.insert("cat");
    dict.insert("and");
    dict.insert("sand");
    dict.insert("dogs");
    Solution sol;
    vector<string> res = sol.wordBreak(s, dict);
    for (auto& s : res) {
        cout << s << endl;
    }
    return 0;
}
