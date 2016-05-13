/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold
additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        int i = m - 1, j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        for (int l = 0; l <= j; ++l) {
            nums1[l] = nums2[l];
        }
    }
};

int main()
{
    vector<int> nums2 = {0, 11, 12, 13, 14};
    int n = nums2.size();
    int m = 5;
    vector<int> nums1(m+n);
    nums1[0] = 1;
    nums1[1] = 3;
    nums1[2] = 5;
    nums1[3] = 7;
    nums1[4] = 9;
    Solution sol;
    sol.merge(nums1, m, nums2, n);
    for (int i = 0; i < m + n; ++i) {
        cout << nums1[i] << ' ';
    }
    cout << endl;
    return 0;
}
