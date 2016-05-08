/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    int strStr(string haystack, string needle) {        
        for (int i = 0; i <= (int)haystack.size()-(int)needle.size(); ++i) {
            int j = 0;
            while (j < (int)needle.size()) {
                if (haystack[i+j] != needle[j]) break;
                ++j;
            }

            if (j == (int)needle.size()) {
                return i;
            }
        }

        return -1;
    }
};

int main()
{
    string haystack = "abdcsdfdsfa";
    string needle = "csd";
    Solution sol;
    int index = sol.strStr(haystack, needle);
    cout << "index = " << index << endl;
    return 0;
}
