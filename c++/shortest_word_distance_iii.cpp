/**
 * Created on 6/5/16.
 * This is a follow up of Shortest Word Distance.
 * The only difference is now word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the
 * shortest distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 */

#include <vector>
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    int shortestWordDistance(const vector<string>& words, const string& word1, const string& word2) {
        bool sameWord = (word1 == word2);
        int minDist = words.size();
        int idx = -1;
        for (int i = 0; i < (int)words.size(); ++i) {
            if (words[i] == word1 || words[i] == word2) {
                if (idx > 0 && (sameWord || words[i] != words[idx])) {
                    minDist = min(minDist, i-idx);
                }
                idx = i;
            }
        }
        return minDist;
    }
};

int main()
{
    vector<string> words = {"practice", "makes", "perfect", "makes", "makes", "coding", "makes"};
    string word1 = "makes", word2 = "coding";
    Solution sol;
    cout << sol.shortestWordDistance(words, word1, word2) << endl;
}

