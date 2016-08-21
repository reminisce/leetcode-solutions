/**
* Created on 7/24/16.
* Given an array, find out the elements
* whose frequencies are more than n/k. (G)
*/

#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

class Solution {
public:
    // the number of majority elements cannot be greater than k
    vector<int> findMajorityElements(const vector<int>& nums, int k) {
        vector<int> res;
        if (nums.empty() || k <= 1) return res;

        unordered_map<int, int> majorityCounts;
        for (int num : nums) {
            auto it = majorityCounts.find(num);
            if (it != majorityCounts.end()) {
                ++it->second;
            } else if (majorityCounts.size() < k-1) {
                majorityCounts[num] = 1;
            } else {
                for (unordered_map<int, int>::iterator mit = majorityCounts.begin(); mit != majorityCounts.end();) {
                    if (mit->second == 1) {
                        mit = majorityCounts.erase(mit);
                    } else {
                        --mit->second;
                        ++mit;
                    }
                }
            }
        }

        for (auto& entry : majorityCounts) {
            entry.second = 0;
        }

        for (int num : nums) {
            auto it = majorityCounts.find(num);
            if (it != majorityCounts.end()) {
                ++it->second;
            }
        }

        for (auto& entry : majorityCounts) {
            if (entry.second > (int)nums.size() / k) {
                res.push_back(entry.first);
            }
        }

        return res;
    }
};

int main()
{
    Solution sol;
    vector<int> nums = {1, 2, 3, 2, 3, 4, 2, 2, 1, 2, 2};
    int k = 3;
    vector<int> res = sol.findMajorityElements(nums, k);
    for (int num : res) {
        cout << num << ' ';
    }
    cout << endl;
    return 0;
}
