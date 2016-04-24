/* Given an array S of n integers, are there elements a, b, c in S such that
 * a + b + c = 0? Find all unique triplets in the array which gives the sum of
 * zero.
 */

#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> res;
        if (nums.size() < 3) {
            return res;
        }
        
        int n = nums.size();
        std::sort(nums.begin(), nums.end());
        if (nums.front() > 0 || nums.back() < 0) {
            return res;
        }
        
        for (size_t i = 0; i < n-2;) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    while (l < r && nums[l] == nums[++l]);
                }
                else if (sum > 0) {
                    while (r > l && nums[r] == nums[--r]);
                }
                else {
                    vector<int> triplet;
                    res.push_back(triplet);
                    res.back().reserve(3);
                    res.back().push_back(nums[i]);
                    res.back().push_back(nums[l]);
                    res.back().push_back(nums[r]);
                    while (l < r && nums[l] == nums[++l]);
                    while (r > l && nums[r] == nums[--r]);
                }
            }
            while (i < n-2 && nums[i] == nums[++i]);
        }
        
        return res;
    }
};

int main(int argc, char** argv) {
    vector<int> nums;
    nums.push_back(-2);
    nums.push_back(-2);
    nums.push_back(-2);
    nums.push_back(0);
    nums.push_back(0);
    nums.push_back(2);
    nums.push_back(2);
    Solution sol;
    vector<vector<int>> ans = sol.threeSum(nums);
    for (size_t i = 0; i < ans.size(); ++i) {
        cout << '(' << ans[i][0] << ", " << ans[i][1] << ", " << ans[i][2] << ')' << endl;
    }
    return 0;
}

