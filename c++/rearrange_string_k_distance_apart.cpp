/**
* Created on 6/30/16.
* Given a non-empty string str and an integer k,
* rearrange the string such that the same characters
* are at least distance k from each other.
*
* All input strings are given in lowercase letters.
* If it is not possible to rearrange the string,
* return an empty string "".
*
* Example 1:
* str = "aabbcc", k = 3
*
* Result: "abcabc"
*
* The same letters are at least distance 3 from each other.
* Example 2:
* str = "aaabc", k = 3
*
* Answer: ""
*
* It is not possible to rearrange the string.
* Example 3:
* str = "aaadbbcc", k = 2
*
* Answer: "abacabcd"
*
* Another possible answer is: "abcabcda"
*
* The same letters are at least distance 2 from each other.
*/

#include <string>
#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

struct FrequencyPairComparator {
    bool operator()(const pair<int, char>& p1, const pair<int, char>& p2) {
        if (p1.first == p2.first) return p1.second > p2.second;
        return p1.first < p2.first;
    }
};

class Solution {
public:
    string rearrangeString(string s, int k) {
        unordered_map<char, int> charCounts;
        for (char c : s) {
            auto it = charCounts.find(c);
            if (it != charCounts.end()) {
                ++it->second;
            } else {
                charCounts[c] = 1;
            }
        }

        priority_queue<pair<int, char>, vector<pair<int, char>>, FrequencyPairComparator> pq;
        for (auto& entry : charCounts) {
            pq.push({entry.second, entry.first});
        }

        string res;
        int remainLength = s.size();
        while (!pq.empty()) {
            string str;
            int cnt = min(remainLength, k);
            vector<pair<int, char>> candidates;
            for (int i = 0; i < cnt; ++i) {
                if (pq.empty()) return "";
                auto p = pq.top();
                pq.pop();
                res.push_back(p.second);
                if (--p.first > 0) {
                    candidates.push_back(p);
                }
                --remainLength;
            }
            for (auto p : candidates) {
                pq.push(p);
            }
            res += str;
        }
        return res;
    }
};

int main()
{
    string s = "abab";
    int k = 2;
    Solution sol;
    cout << sol.rearrangeString(s, k) << endl;
    return 0;
}

