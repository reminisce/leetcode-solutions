/*
Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from:
Permutations II or Next Permutation.
*/

#include <unordered_map>
#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> generatePalindromes(string s) {
        vector<string> res;
        unordered_map<char, int> charCountMap;
        for (char c : s) {
            auto it = charCountMap.find(c);
            if (it == charCountMap.end()) {
                charCountMap[c] = 1;
            } else {
                ++(it->second);
            }
        }

        string mid = "";
        string str = ""; // first half string except the mid char if s.size() is odd
        for (auto& p : charCountMap) {
            if (p.second & 1) {
                mid += p.first;
            }
            for (int i = 0; i < p.second/2; ++i) {
                str += p.first;
            }
        }

        vector<string> strs; // permuations of str
        permute(str, 0, mid, strs);
        return strs;
    }

    void permute(string& str, int index, const string& mid, vector<string>& strs) {
        if (index >= str.size()) {
            strs.push_back(str + mid + string(str.rbegin(), str.rend()));
            return;
        }

        for (int i = index; i < str.size(); ++i) {
            if (i > index && hasDuplicate(str, index, i, str[i])) continue;
            swap(str[i], str[index]);
            permute(str, index+1, mid, strs);
            swap(str[i], str[index]);
        }
    }

    bool hasDuplicate(const string& str, int start, int end, char target) {
        for (int i = start; i < end; ++i) {
            if (str[i] == target) {
                return true;
            }
        }
        return false;
    }
};

int main()
{
    string str = "aaaab";
    Solution sol;
    vector<string> strs = sol.generatePalindromes(str);
    for (string& str : strs) {
        cout << str << endl;
    }
    return 0;
}
