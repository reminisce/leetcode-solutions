/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> combine(int n, int k) {      
        vector<vector<int>> rs;
        vector<int> comb;
        comb.reserve(k);
        combine(1, n, k, rs, comb);

        return rs;
    }

    void combine(int start, int n, int k, vector<vector<int>>& rs, vector<int>& comb) {
        if (k == 0) {
            rs.push_back(comb);
            return;
        }

        for (int i = start; i <= n; ++i) {
            comb.push_back(i);
            combine(i+1, n, k-1, rs, comb);
            comb.pop_back();
        }
    }
};

int main()
{
    Solution sol;
    int n = 2;
    int k = 1;
    vector<vector<int>> rs = sol.combine(n, k);
    for (vector<int>& r : rs) {
        for (int v : r) {
            cout << v << ' ';
        }
        cout << endl;
    }
    return 0;
}
