/*
(G)
Given a source string and order string without
repeated characters. Validate whether
the characters in the source string
follow the order of them in the
order string.
*/

#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    bool validateString(const string& str, const string& order) {
        char m[26] = {0};
        for (size_t i = 0; i < order.size(); ++i) {
            m[(int)(order[i]-'a')] = i + 1;
        }

        int preVal = 0;
        for (size_t i = 0; i < str.size(); ++i) {
            int curVal = m[(int)(str[i]-'a')];
            if (curVal > 0) {
                if (preVal > curVal) return false;
                preVal = curVal;
            }
        }
        return true;
    }
};

int main()
{
    string str = "gogollrefo";
    string order = "ole";
    Solution sol;
    cout << sol.validateString(str, order) << endl;
    return 0;
}