/**
* Created on 7/16/16.
* Numbers can be regarded as product
* of its factors. For example,
*
* 8 = 2 x 2 x 2;
* = 2 x 4.
* Write a function that takes an integer n and return all possible combinations of its factors.
*
* Note:
*
* Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
* You may assume that n is always positive.
* Factors should be greater than 1 and less than n.
*
*
* Examples:
* input: 1
* output:
*
* []
* input: 37
*
* output:
*
* []
* input: 12
* output:
*
* [
* [2, 6],
* [2, 2, 3],
* [3, 4]
* ]
* input: 32
* output:
*
* [
* [2, 16],
* [2, 2, 8],
* [2, 2, 2, 4],
* [2, 2, 2, 2, 2],
* [2, 4, 4],
* [4, 8]
* ]
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> getFactors(int n) {
        vector<vector<int>> res;
        vector<int> factorCombo;
        getFactorsHelper(n, n, 2, factorCombo, res);
        return res;
    }

    void getFactorsHelper(int originalNum, int currentNum, int start,
                          vector<int>& factorCombo, vector<vector<int>>& res) {
        if (currentNum <= 1) {
            res.push_back(factorCombo);
            return;
        }

        for (int i = start; i <= currentNum && i < originalNum; ++i) {
            if (currentNum % i == 0) {
                factorCombo.push_back(i);
                getFactorsHelper(originalNum, currentNum/i, i, factorCombo, res);
                factorCombo.pop_back();
            }
        }
    }
};

int main()
{
    Solution sol;
    int n = 12;
    vector<vector<int>> res = sol.getFactors(n);
    for (auto& v : res) {
        for (int i : v) {
            cout << i << ' ';
        }
        cout << endl;
    }
    return 0;
}