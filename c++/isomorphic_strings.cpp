#include <cstdlib>
#include <stdio.h>
#include <string>
#include <cstring>
#include <iostream>

using namespace std;

class Solution {
public:

    bool isIsomorphic(string s, string t) {
        int m1[256] = {0};
        int m2[256] = {0};

        //Complexity : O(n)
        for (int i = 0; i < s.length(); ++i) {
            if (m1[s[i]] > 0 && m2[t[i]] > 0) {
                if (m1[s[i]] != m2[t[i]]) return false;
            } else if (m1[s[i]] == 0 && m2[t[i]] == 0) {
                m1[s[i]] = m2[t[i]] = i+1;
            } else {
                return false;
            }
        }
        return true;
    }
};

int main(int argc, char** argv) {
    Solution solution;
    bool ret = solution.isIsomorphic("ab", "aa");
    if (ret)
        cout << "is isomorphic" << endl;
    else
        cout << "is not isomorphic" << endl;
    return 0;
}

