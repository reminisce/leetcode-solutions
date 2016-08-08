/**
* Created on 7/11/16.
* Given a string that contains only digits 0-9
* and a target value, return all possibilities
* to add binary operators (not unary) +, -, or
* * between the digits so they evaluate to the
* target value.
*
* Examples:
* "123", 6 -> ["1+2+3", "1*2*3"]
* "232", 8 -> ["2*3+2", "2+3*2"]
* "105", 5 -> ["1*0+5","10-5"]
* "00", 0 -> ["0+0", "0-0", "0*0"]
* "3456237490", 9191 -> []
*/

#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> addOperators(string num, int target) {
        vector<string> res;
        addOperatorsHelper(num, 0, target, 0, 0, "", res);
        return res;
    }

    void addOperatorsHelper(const string& num, int start, int target,
                            long preNum, long curRes,
                            string expression, vector<string>& res) {
        if (start == (int)num.size()) {
            if (curRes == target) {
                res.push_back(expression);
            }
            return;
        }

        for (int i = start; i < (int)num.size(); ++i) {
            if (num[start] == '0' && i > start) return;
            string str = num.substr(start, i-start+1);
            long curNum = stol(str);
            if (expression.empty()) {
                addOperatorsHelper(num, i+1, target, curNum, curNum, str, res);
            } else {
                addOperatorsHelper(num, i+1, target, preNum*curNum, (curRes-preNum)+preNum*curNum,
                                   expression+'*'+to_string(curNum), res);
                addOperatorsHelper(num, i+1, target, curNum, curRes+curNum,
                                   expression+'+'+to_string(curNum), res);
                addOperatorsHelper(num, i+1, target, -curNum, curRes-curNum,
                                   expression+'-'+to_string(curNum), res);
            }
        }
    }
};

int main()
{
    string s = "3456237490";
    int target = 9191;
    Solution sol;
    vector<string> res = sol.addOperators(s, target);
    for (auto& expression : res) {
        cout << expression << endl;
    }
    return 0;
}
