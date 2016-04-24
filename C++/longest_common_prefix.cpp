/* Write a function to find the longest common prefix string amongst an array of strings. */

#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.empty()) {
            return "";
        }
        
        int prefix_len = strs[0].length();
        for (size_t i = 1; i < strs.size(); ++i) {
            int j = 0;
            while (j < strs[i].length() && j < prefix_len && strs[i][j] == strs[0][j]) {
                ++j;
            }
            prefix_len = j;
        }
        
        return strs[0].substr(0, prefix_len);
    }
};

int main(int argc, char** argv) {
    vector<string> strs;
    strs.push_back("abc");
    strs.push_back("abd");
    strs.push_back("acd");
    Solution sol;
    string prefix = sol.longestCommonPrefix(strs);
    cout << prefix << endl;
    return 0;
}

