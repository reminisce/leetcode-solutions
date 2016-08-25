/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5,
with the first five elements of nums being
1, 1, 2, 2 and 3. It doesn't matter what
you leave beyond the new length.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:

    int removeDuplicates(vector<int>& nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i-2]) {
                nums[i++] = num;
            }
        }
        return i;
    }

    int removeDuplicates2(vector<int>& nums) {
        if (nums.empty()) return 0;
        int cnt = 1;
        int preNum = nums[0];
        int lastIdx = 0;
        int curIdx = 1;
        while (curIdx < (int)nums.size()) {
            if (nums[curIdx] != preNum) {
                nums[++lastIdx] = nums[curIdx];
                cnt = 1;
                preNum = nums[lastIdx];
            } else if (cnt < 2) {
                nums[++lastIdx] = nums[curIdx];
                ++cnt;
            }
            ++curIdx;
        }
        return lastIdx + 1;
    }
};

int main()
{
    vector<int> nums = {1, 1, 1, 2, 2, 3};
    Solution sol;
    cout << sol.removeDuplicates(nums) << endl;
    return 0;
}
