/*
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.

Hint:

Try two pointers.
Did you use the property of "the order of elements can be changed"?
What happens when the elements to remove are rare?
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int lastIdx = -1;
        int curIdx = 0;
        while (curIdx < nums.size()) {
            while (curIdx < nums.size() && nums[curIdx] == val) {
                ++curIdx;
            }

            if (curIdx == nums.size()) break;

            nums[++lastIdx] = nums[curIdx++];
        }

        return lastIdx + 1;
    }
};

int main()
{
    vector<int> nums = {3, 2, 2, 3};
    int val = 2;
    Solution sol;
    int len = sol.removeElement(nums, val);
    for (int i = 0; i < len; ++i) {
        cout << nums[i] << ' ';
    }
    cout << endl;
    return 0;
}