/*
Given a string S and a string T, find the minimum window in S which will contain all the characters
in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
public:
    /**
     * Start from the first letter, and keep
     * scanning until the window covers all
     * the letters of t. Then shrink the window
     * by incrementing the window's left index
     * until the minimum. Then, increment 
     */
    string minWindow(string s, string t) {
        unordered_map<char, int> patternCharCount; // the count of char in t
        for (char c : t) {
            auto it = patternCharCount.find(c);
            if (it != patternCharCount.end()) {
                ++(it->second);
            } else {
                patternCharCount[c] = 1;
            }
        }

        int minWindowLength = -1;
        int minWindowStart = -1;
        int wl = 0, wr = 0; // window left and right index 
        int count = 0; // count of the chars of t in current window
        unordered_map<char, int> windowCharCount; // the count of char in the window
        for (int wr = 0; wr < (int)s.size(); ++wr) {
            auto pit = patternCharCount.find(s[wr]);
            if (pit != patternCharCount.end()) {
                int curCharCount = 0;
                auto wit = windowCharCount.find(s[wr]);
                if (wit != windowCharCount.end()) {
                    ++(wit->second);
                    curCharCount = wit->second;
                } else {
                    windowCharCount[s[wr]] = 1;
                    curCharCount = 1;
                }
                // Increment count by one if the window
                // has not covered more chars than that of t.
                // The variable count is used to determine
                // The first valid window. After that,
                // the count will not be updated, since
                // we will keep the current window minimum
                // and valid.
                if (curCharCount <= pit->second) {
                    ++count;
                }
            }

            // Have found such a window, now
            // try to minimize the current window
            if (count == (int)t.size()) {
                auto pit = patternCharCount.find(s[wl]);
                auto wit = windowCharCount.find(s[wl]);
                // shrink the window if currrent char is not in t
                // or the current char contained in the window is
                // more than in the pattern string t.
                while (pit == patternCharCount.end() || wit->second > pit->second) {
                    if (wit != windowCharCount.end()) {
                        --(wit->second);
                    }
                    ++wl;
                    pit = patternCharCount.find(s[wl]);
                    wit = windowCharCount.find(s[wl]);
                }

                if (minWindowLength < 0 || minWindowLength > (wr - wl + 1)) {
                    minWindowLength = wr - wl + 1;
                    minWindowStart = wl;
                }
            }
        }

        if (minWindowStart < 0) {
            return "";
        }

        return s.substr(minWindowStart, minWindowLength);
    }
};

int main()
{
    string s = "ADOBECODEBANC";
    string t = "ABC";
    Solution sol;
    string window = sol.minWindow(s, t);
    cout << "s = " << s << ", t = " << t << ", window = " << window << endl;
    return 0;
}



















