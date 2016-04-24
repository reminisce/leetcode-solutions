#include <vector>
#include <deque>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> result;
        if (k <= 0 || nums.empty()) {
            return result;
        }

        deque<int> windowCache; // stores the candidates' indices
        size_t maxNumIdx = 0; // max value's index per window
        if (k > nums.size()) {
            return result;
        }

        result.resize((int)nums.size()-k+1);
        // start to move sliding window
        for (size_t i = 0; i < k; ++i) {
            while (!windowCache.empty() && nums[i] >= nums[windowCache.back()]) {
                windowCache.pop_back();
            }
            windowCache.push_back(i);
        }
        result[0] = nums[windowCache.front()];
        while (!windowCache.empty() && windowCache.front() <= 0) {
            windowCache.pop_front();
        }


        for (size_t i = 1; i <= (int)nums.size()-k; ++i) {
            // pop out indices whose numbers are less than the new value just into the window
            int j = i + k - 1;
            while (!windowCache.empty() && nums[j] >= nums[windowCache.back()]) {
                windowCache.pop_back();
            }
            windowCache.push_back(j);
            
            result[i] = nums[windowCache.front()];
            // pop out indices that are out of the next sliding window
            while (!windowCache.empty() && windowCache.front() <= i) {
                windowCache.pop_front();
            }
        }

        return result;
    }
};

int main()
{
    vector<int> nums = { 1, -1 };
    int k = 1;
    Solution sol;
    vector<int> res = sol.maxSlidingWindow(nums, k);
    return 0;
}


