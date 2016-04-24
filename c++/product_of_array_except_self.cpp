#include <cstdlib>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        if (nums.empty()) {
            return nums;
        }
        
        vector<int> res(nums.size());
        
        for (int i = 0, prod = 1; i < (int)nums.size(); prod *= nums[i++]) {
            res[i] = prod;
        }
        
        for (int i = (int)nums.size()-1, prod = 1; i >= 0; prod *= nums[i--]) {
            res[i] *= prod;
        }
        
        return res;
    }
};

int main(int argc, char** argv) {

    vector<int> nums;
    nums.push_back(0);
    nums.push_back(1);
    Solution sol;
    nums = sol.productExceptSelf(nums);
    for (size_t i = 0; i < nums.size(); ++i) {
        cout << nums[i] << ' ';
    }
    cout << endl;
    return 0;
}

