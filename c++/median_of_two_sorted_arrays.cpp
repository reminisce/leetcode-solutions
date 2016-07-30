/**
* Created on 7/9/16.
* There are two sorted arrays nums1
* and nums2 of size m and n respectively.
*
* Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*
* Example 1:
* nums1 = [1, 3]
* nums2 = [2]
*
* The median is 2.0
* Example 2:
* nums1 = [1, 2]
* nums2 = [3, 4]
*
* The median is (2 + 3)/2 = 2.5
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int n1 = nums1.size(), n2 = nums2.size();
        if ((n1+n2) % 2 == 0) {
            return (findeMedianHelper(nums1, 0, nums2, 0, (n1+n2)/2)
                    + findeMedianHelper(nums1, 0, nums2, 0, (n1+n2)/2+1)) * 0.5;
        } else {
            return findeMedianHelper(nums1, 0, nums2, 0, (n1+n2)/2+1);
        }
    }

    double findeMedianHelper(const vector<int>& nums1, int start1,
                             const vector<int>& nums2, int start2,
                             int k) {
        if (nums1.size() - start1 > nums2.size() - start2) {
            return findeMedianHelper(nums2, start2, nums1, start1, k);
        }
        if (nums1.size() == start1) return nums2[start2+k-1];
        if (k == 1) return min(nums1[start1], nums2[start2]);
        int mid1 = min(start1 + k / 2, (int)nums1.size());
        int mid2 = start2 + (k - (mid1 - start1));
        if (nums1[mid1-1] < nums2[mid2-1]) {
            return findeMedianHelper(nums1, mid1, nums2, start2, k-mid1+start1);
        } else if (nums1[mid1-1] > nums2[mid2-1]) {
            return findeMedianHelper(nums1, start1, nums2, mid2, k-mid2+start2);
        } else {
            return nums1[mid1-1];
        }
    }
};

int main()
{
    vector<int> nums1 = {1, 2};
    vector<int> nums2 = {3, 4};
    Solution sol;
    cout << sol.findMedianSortedArrays(nums1, nums2) << endl;
    return 0;
}
