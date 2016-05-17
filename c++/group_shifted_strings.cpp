/*
Given a string, we can "shift" each of its letter to its successive letter,
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings
that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/

#include <string>
#include <unordered_map>
#include <vector>
#include <set>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * If two strings can be grouped, they have the same
     * difference value of every char with their own first char.
     * Difference is calculated by adding 26 to the direct difference
     * and mod by 26. So we can convert the difference to key
     * in the hash map.
     */
    vector<vector<string>> groupStrings(vector<string>& strings) {
        vector<vector<string>> res;
        unordered_map<string, multiset<string>> stringMap;
        for (string& str : strings) {
            string key;
            for (char c : str) {
                key += to_string(((int)(c-str[0])+26)%26) + '/';
            }
            stringMap[key].insert(str);
        }

        for (auto it = stringMap.begin(); it != stringMap.end(); ++it) {
            res.push_back(vector<string>(it->second.begin(), it->second.end()));
        }

        return res;
    }
};

int main()
{
    vector<string> strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
    Solution sol;
    vector<vector<string>> res = sol.groupStrings(strs);
    for (auto& v : res) {
        for (auto& s : v) {
            cout << s << ' ';
        }
        cout << endl;
    }
    return 0;
}
