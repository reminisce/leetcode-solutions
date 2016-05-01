/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:

    /**
     * Keep tracking the max product ending at each
     * array element and update maxProd accordingly.
     * Due to negative numbers, we also need to keep
     * tracking the minProd ending at each element.
     */
    int maxProduct(vector<int>& nums) { 
        if (nums.empty()) {
            return 0;
        }

        int curMax = nums[0];
        int curMin = nums[0];
        int maxProd = curMax;

        for (size_t i = 1; i < nums.size(); ++i) {
            int temp = curMax;
            // update curMax
            curMax = max(max(curMax*nums[i], curMin*nums[i]), nums[i]);
            // update curMin
            curMin = min(min(temp*nums[i], curMin*nums[i]), nums[i]);
            maxProd = max(curMax, maxProd);
        }

        return maxProd;
    }
};

int main()
{
    vector<int> nums = {-4, -3, -2};
    Solution sol;
    int maxProd = sol.maxProduct(nums);
    cout << "Max product is " << maxProd << endl;
    return 0;
}