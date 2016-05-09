/*
Given a sorted array, remove the duplicates in place such that each element
appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being
1 and 2 respectively. It doesn't matter what you leave beyond the new length.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if (nums.empty()) return 0;

        int lastIdx = 0;
        int curIdx = 1;

        while (curIdx < (int)nums.size()) {
            while (curIdx < (int)nums.size() && nums[curIdx] == nums[lastIdx]) {
                ++curIdx;
            }

            if (curIdx == (int)nums.size()) break;
            nums[++lastIdx] = nums[curIdx];
        }

        return lastIdx + 1;
    }
};

int main()
{
    vector<int> nums = {1, 1, 1};
    Solution sol;
    int length = sol.removeDuplicates(nums);
    for (int i = 0; i < length; ++i) {
        cout << nums[i] << ' ';
    }
    cout << endl;
    return 0;
}