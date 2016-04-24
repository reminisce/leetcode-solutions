#include <cstdlib>
#include <stdio.h>
#include <string>
#include <cstring>
#include <iostream>

using namespace std;

class Solution {
public:

    bool isIsomorphic(string s, string t) {
        char iso_map[26];
        memset(iso_map, '0', 26);
        for (size_t i = 0; i < s.size(); ++i) {
            int index = (int) (s[i] - 'a');
            if (iso_map[index] == '0') {
                iso_map[index] = t[i];
            } else if (iso_map[index] != t[i]) {
                return false;
            }
        }
        return true;
    }

    bool isIsomorphic2(string s, string t) {
        char shash[256] = {0};
        char thash[256] = {0};

        //Complexity : O(n)
        for (int i = 0; i < s.length(); ++i) {
            //Bi-direction hashing

            /* Hash From s to t */
            if (shash[s[i]] != 0) {
                if (shash[s[i]] != t[i])return false;
            } else shash[s[i]] = t[i];

            /* Hash From t to s */
            if (thash[t[i]] != 0) {
                if (thash[t[i]] != s[i])return false;
            } else thash[t[i]] = s[i];
        }
        return true;
    }
};

int main(int argc, char** argv) {
    Solution solution;
    bool ret = solution.isIsomorphic("aa", "ab");
    if (ret)
        cout << "is isomorphic" << endl;
    else
        cout << "is not isomorphic" << endl;
    return 0;
}

