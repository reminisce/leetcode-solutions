/**
* Created on 6/5/16.
* A strobogrammatic number is a number that looks
* the same when rotated 180 degrees (looked at upside down).
*
* Find all strobogrammatic numbers that are of length = n.
*
* For example,
* Given n = 2, return ["11","69","88","96"].
*
* Hint:
*
* Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

#include <queue>
#include <vector>
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> findStrobogrammatic(int n) {
        vector<string> res;
        if (n <= 0) return res;
        queue<string> q;
        int curLen = 0;
        if (n % 2 == 1) {
            q.push("0");
            q.push("1");
            q.push("8");
            curLen = 1;
        } else {
            if (n > 2) {
                q.push("00");
            }
            q.push("11");
            q.push("69");
            q.push("88");
            q.push("96");
            curLen = 2;
        }

        while (curLen < n) {
            curLen += 2;
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                string curNum = q.front();
                q.pop();
                if (curLen != n) {
                    q.push('0' + curNum + '0');
                }
                q.push('1' + curNum + '1');
                q.push('6' + curNum + '9');
                q.push('8' + curNum + '8');
                q.push('9' + curNum + '6');
            }
        }

        while (!q.empty()) {
            res.push_back(q.front());
            q.pop();
        }
        return res;
    }
};

int main()
{
    Solution sol;
    int n = 4;
    vector<string> res = sol.findStrobogrammatic(n);
    for (string num : res) {
        cout << num << ' ';
    }
    cout << endl;
    return 0;
}
