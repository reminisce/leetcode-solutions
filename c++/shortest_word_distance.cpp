/**
* Created on 6/5/16.
* Given a list of words and two words word1 and word2,
* return the shortest distance between these two words in the list.
*
* For example,
* Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
*
* Given word1 = “coding”, word2 = “practice”, return 3.
* Given word1 = "makes", word2 = "coding", return 1.
*
* Note:
* You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int shortestDistance(const vector<string>& strs, const string& str1, const string& str2) {
        int idx = -1;
        int dist = strs.size() + 1;
        for (int i = 0; i < strs.size(); ++i) {
            if (strs[i] == str1 || strs[i] == str2) {
                if (idx < 0) {
                    idx = i;
                } else if (strs[i] != strs[idx]) {
                    dist = min(dist, i - idx);
                    idx = i;
                }
            }
        }
        return dist;
    }
};

int main()
{
    vector<string> strs = {"practice", "makes", "perfect", "coding", "makes"};
    string str1 = "coding";
    string str2 = "makes";
    Solution sol;
    cout << sol.shortestDistance(strs, str1, str2) << endl;
}
