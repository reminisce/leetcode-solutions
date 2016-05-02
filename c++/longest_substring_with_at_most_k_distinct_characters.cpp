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
        if (str.empty() || k <= 0) {
            return 0;
        }
        int maxLength = 1;
        int wl = 0, wr = 0; // window left and right
        unordered_map<char, int> charMap;
        int i = 0; // window's left index
        int j = 0; // window's right index
        while (j < str.size()) {
            auto it = charMap.find(str[j]);
            if (it != charMap.end()) {
                ++(it->second);
            } else if (charMap.size() < k) {
                charMap[str[j]] = 1;
            } else {
                while (i < j) {
                    auto it1 = charMap.find(str[i]); 
                    if (it1->second > 1) {
                        --(it1->second);
                        ++i;
                    } else {
                        charMap.erase(it1);
                        charMap[str[j]] = 1;
                        ++i;
                        break;
                    }
                }
            }

            // debug
            if (j-i+1 > maxLength) {
                wl = i;
                wr = j;
            }
            // end of debug

            maxLength = max(maxLength, j-i+1);
            ++j;
        }

        cout << "Longest substring with " << k << " different characters is " << str.substr(wl, maxLength) << endl;

        return maxLength;
    }
};

int main()
{
    string str = "ecebaaeeedeebbbb";
    int k = 5;
    Solution sol;
    int length = sol.longestSubstring(str, k);
    cout << "Length = " << length << endl;
    return 0;
}
