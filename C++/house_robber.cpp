#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int rob(vector<int>& nums) {
        const int n = nums.size();
        if (n == 0) {
            return 0;
        }
        
        bool is_last_robbed = false;
        
        int money[n];
        
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                money[i] = nums[i];
                is_last_robbed = true;
                continue;
            }
            
            if (i == 1) {
                if (nums[i] > nums[i-1]) {
                    money[i] = nums[i];
                    is_last_robbed = true;
                }
                else {
                    money[i] = money[i-1];
                    is_last_robbed = false;
                }
                continue;
            }
            
            if (is_last_robbed) {
                int tmp = nums[i] + money[i-2];
                if (tmp > money[i-1]) {
                    money[i] = tmp;
                    is_last_robbed = true;
                }
                else {
                    money[i] = money[i-1];
                    is_last_robbed = false;
                }
            }
            else {
                money[i] = money[i-1] + nums[i];
                is_last_robbed = true;
            }
        }
        return money[n-1];
    }
};
int main(int argc, char** argv) {

    vector<int> nums;
    nums.push_back(1);
    nums.push_back(1);
    nums.push_back(1);
    nums.push_back(1);
    
    Solution solution;
    int ret = solution.rob(nums);
    cout << "ret = " << ret << endl;
    return 0;
}

