/**
* (G)
* Created on 6/21/16.
* Given a positive integer n,
* output all the strobogrammatic numbers whose
* length is not greater than n.
*/

#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

class Solution {
public:
    vector<string> findStrobogrammatic(int n) {
        vector<string> res;
        if (n <= 0) return res;

        queue<string> q;
        q.push("0");
        q.push("1");
        q.push("8");
        int curOddLen = 1;

        int curEvenLen = 0;
        if (n >= 2) {
            q.push("00");
            q.push("11");
            q.push("69");
            q.push("88");
            q.push("96");
            curEvenLen = 2;
        }

        while (curOddLen <= n || curEvenLen <= n) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                string str = q.front();
                q.pop();
                if (str.size() <= n && (str.size() == 1 || str[0] != 0)) {
                    res.push_back(str);
                }
                if (str.size() + 2 <= n) {
                    q.push('0' + str + '0');
                    q.push('1' + str + '1');
                    q.push('6' + str + '9');
                    q.push('8' + str + '8');
                    q.push('9' + str + '6');
                }
            }
            curOddLen += 2;
            curEvenLen += 2;
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
