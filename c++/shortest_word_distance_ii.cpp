/**
* Created on 6/5/16.
* This is a follow up of Shortest Word Distance. The only difference is now you
* are given the list of words and your method will be called repeatedly many times
* with different parameters. How would you optimize it?
*
* Design a class which receives a list of words in the constructor, and implements
* a method that takes two words word1 and word2 and return the shortest distance
* between these two words in the list.
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

#include <vector>
#include <string>
#include <unordered_map>
#include <iostream>

using namespace std;

class ShortestWordDistanceII {
public:
    ShortestWordDistanceII(const vector<string>& words) : m_wordListLength(words.size()) {
        for (int i = 0; i < words.size(); ++i) {
            m_wordIndexMap[words[i]].push_back(i);
        }
    }

    int shortest(const string& word1, const string& word2) {
        const vector<int>& indices1 = m_wordIndexMap[word1];
        const vector<int>& indices2 = m_wordIndexMap[word2];
        int distance = m_wordListLength;
        int i = 0, j = 0;
        while (i < indices1.size() && j < indices2.size()) {
            int d = abs(indices1[i]-indices2[j]);
            if (d == 1) return d;
            distance = min(d, distance);
            if (indices1[i] < indices2[j]) ++i;
            else ++j;
        }
        return distance;
    }

private:
    int m_wordListLength;
    unordered_map<string, vector<int>> m_wordIndexMap;
};

int main() {
    vector<string> words = {"practice", "makes", "perfect", "coding", "makes"};
    ShortestWordDistanceII app(words);
    string word1 = "coding";
    string word2 = "makes";
    cout << app.shortest(word1, word2) << endl;
    return 0;
}