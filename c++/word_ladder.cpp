/*
Given two words (beginWord and endWord), and a dictionary's word list, find the
length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

#include <iostream>
#include <queue>
#include <unordered_set>
#include <string>

using namespace std;

class Solution {
public:
    int ladderLength(string beginWord, string endWord, unordered_set<string>& wordList) {      
        if (beginWord == endWord) {
            return 0;
        }

        queue<string> q;
        q.push(beginWord);
        int level = 1;

        // do BFS
        while (!q.empty()) {
            ++level;
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                string str = q.front();
                q.pop();

                for (size_t j = 0; j < str.size(); ++j) {
                    for (int k = 0; k < 26; ++k) {
                        char changedChar = char(k+'a');
                        if (changedChar != str[j]) {
                            string changedStr = str;
                            changedStr[j] = changedChar;
                            if (changedStr == endWord) {
                                return level;
                            } else {
                                auto it = wordList.find(changedStr);
                                if (it != wordList.end()) {
                                    q.push(changedStr);
                                    wordList.erase(it); // Need to remove changedStr to avoid infinite loop
                                }
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }
};

int main()
{
    string beginWord = "hit";
    string endWord = "cog";
    unordered_set<string> wordList;
    wordList.insert("hot");
    wordList.insert("dot");
    wordList.insert("dog");
    wordList.insert("lot");
    wordList.insert("log");
    Solution sol;
    int length = sol.ladderLength(beginWord, endWord, wordList);
    cout << "Word ladder length = " << length << endl;
    return 0;
}
