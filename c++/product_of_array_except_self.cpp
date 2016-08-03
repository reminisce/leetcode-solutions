/**
 * Given a vector of n elements,
 * choose k elements (k < n) from
 * it such that every element in
 * the vector is considered uniformly.
 */

#include <vector>

using namespace std;

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n, 1); 
        for (int i = 1; i < n; ++i) {
            res[i] = res[i-1]*nums[i-1];
        }   

        int prod = 1;
        for (int i = n-2; i >= 0; --i) {
            prod *= nums[i+1];
            res[i] *= prod;
        }
        return res;
    }
};
