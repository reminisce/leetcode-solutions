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

        queue<string> oddQueue;
        oddQueue.push("0");
        oddQueue.push("1");
        oddQueue.push("8");
        int curOddLen = 1;

        queue<string> evenQueue;
        evenQueue.push("00");
        evenQueue.push("11");
        evenQueue.push("69");
        evenQueue.push("88");
        evenQueue.push("96");
        int curEvenLen = 2;

        while ((n % 2 == 1 && curOddLen <= n) || (n % 2 == 0 && curEvenLen <= n)) {
            curOddLen += 2;
            int sz = oddQueue.size();
            for (int i = 0; i < sz; ++i) {
                string str = oddQueue.front();
                oddQueue.pop();
                if (str.size() == 1 || str[0] != '0') {
                    res.push_back(str);
                }
                if (curOddLen <= n) {
                    oddQueue.push('0' + str + '0');
                    oddQueue.push('1' + str + '1');
                    oddQueue.push('6' + str + '9');
                    oddQueue.push('8' + str + '8');
                    oddQueue.push('9' + str + '6');
                }
            }
            if (n % 2 == 1 && curOddLen > n) break;

            curEvenLen += 2;
            sz = evenQueue.size();
            for (int i = 0; i < sz; ++i) {
                string str = evenQueue.front();
                evenQueue.pop();
                if (str[0] != '0') {
                    res.push_back(str);
                }
                if (curEvenLen <= n) {
                    evenQueue.push('0' + str + '0');
                    evenQueue.push('1' + str + '1');
                    evenQueue.push('6' + str + '9');
                    evenQueue.push('8' + str + '8');
                    evenQueue.push('9' + str + '6');
                }
            }
            if (n % 2 == 0 && curEvenLen > n) break;
        }

        return res;
    }
};

int main()
{
    Solution sol;
    int n = 3;
    vector<string> res = sol.findStrobogrammatic(n);
    for (string num : res) {
        cout << num << ' ';
    }
    cout << endl;
    return 0; 
}
