/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same
color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    void sortColors(vector<int>& nums) {
        // left is the red bound index+1, right is the blue bound index-1
        int left = 0, right = (int)nums.size() - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums[i++], nums[left++]);
            } else if (nums[i] == 2) {
                swap(nums[i], nums[right--]);
            } else {
                ++i;
            }
        }
    }
};

int main()
{
    vector<int> nums = {2, 1, 0, 2, 1, 0, 1, 0};
    Solution sol;
    sol.sortColors(nums);
    for (int c : nums) {
        cout << c << ' ';
    }
    cout << endl;
    return 0;
}
