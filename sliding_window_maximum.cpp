#include <cstdlib>
#include <deque>
#include <vector>
#include <iostream>

using namespace std;

/* Put the largest number's index of the current window
 * at the front of the queue and remove the numbers from
 * the queue when they are less than the next number in
 * the array.
 */
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> res;
        int n = nums.size();
        if (k == 0 || n == 0) {
            return res;
        }
        
        res.resize(n-k+1);
        deque<int> q;
        
        for (int i = 0; i < k; ++i) {
            // Remove the numbers from the queue that are
            // less than the current number. Since the current
            // number stays longer than its previous numbers
            // in the sliding window. Removing previous numbers
            // will not cause any problems.
            while (!q.empty() && nums[i] > nums[q.back()]) {
                q.pop_back();
            }
            q.push_back(i);
        }
        
        for (int i = k; i < nums.size(); ++i) {
            res[i-k] = nums[q.front()];
            // Remove the numbers from the queue that are
            // less than the current number. Since the current
            // number stays longer than its previous numbers
            // in the sliding window. Removing previous numbers
            // will not cause any problems.
            while (!q.empty() && nums[i] > nums[q.back()]) {
                q.pop_back();
            }
            // Remove the numbers that will be out of the next
            // sliding window
            while (!q.empty() && q.front() <= i-k) {
                q.pop_front();
            }
            q.push_back(i);
        }
        res[n-k] = nums[q.front()];
    }
};

int main(int argc, char** argv) {
    // Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
    vector<int> nums;
    nums.push_back(1);
    nums.push_back(3);
    nums.push_back(-1);
    nums.push_back(-3);
    nums.push_back(5);
    nums.push_back(3);
    nums.push_back(6);
    nums.push_back(7);
    int k = 3;
    Solution sol;
    vector<int> res = sol.maxSlidingWindow(nums, k);
    
    for (size_t i = 0; i < res.size(); ++i) {
        cout << "Max number of sliding window " << i << ": " << res[i] << endl;
    }
    
    return 0;
}

