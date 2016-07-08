/*
Example
For example, Given s = "eceba", k = 3,
T is "eceb" which its length is 4.

Challenge
O(n), n is the size of the string s.
*/

#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    /**
     * Maintain a sliding window starting from the first
     * character. Use a hashmap to record every character's
     * frequency in the window. Each time, increment the window's
     * right index by one and insert the character just coming into
     * the window into the hashmap. If the hashmap's size is greater than
     * k, it means there are more than k distince characters, and
     * we need to increment window's left index till the map's size
     * is equal to k.
     */
    int longestSubstring(const string& str, int k) {
        if (k < 1) return 0;
        unordered_map<char, int> charCountMap;
        int left = 0;
        int right = left;
        int maxLen = 1;
        while (right < str.size()) {
            auto it = charCountMap.find(str[right]);
            if (it != charCountMap.end()) {
                ++it->second;
            } else {
                charCountMap[str[right]] = 1;
            }

            while (charCountMap.size() > k) {
                it = charCountMap.find(str[left++]);
                if (it->second == 1) charCountMap.erase(it);
                else it->second--;
            }

            maxLen = max(maxLen, right - left + 1);
            ++right;
        }
        return maxLen;
    }
};

int main()
{
    string str = "ecebaaaaaaaccccccdbbbbb";
    int k = 2;
    Solution sol;
    int length = sol.longestSubstring(str, k);
    cout << "Length = " << length << endl;
    return 0;
}
