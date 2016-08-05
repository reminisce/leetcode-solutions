/*
Given three arrays a, b, and c, find
three indices i, j, k from those three
arrays, repectively, such that
max(abs(a[i]-b[j]), abs(a[i]-c[k]), abs(b[j]-c[k]))
is the minimum.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int findClosest(const vector<int>& nums1, const vector<int>& nums2, const vector<int>& nums3) {
        int diff = INT_MAX;
        int k1 = k2 = k3 = 0; // result indices
        int i1 = i2 = i3 = 0;
        while (i1 < (int)nums1.size() && i2 < (int)nums2.size() && i3 < (int)nums3.size()) {
            int minVal = min(nums1[i1], min(nums2[i2], nums3[i3]));
            int maxVal = max(nums1[i1], max(nums2[i2], nums3[i3]));

            if (maxVal - minVal < diff) {
                k1 = i1;
                k2 = i2;
                k3 = i3;
            }

            if (nums[i1] == minVal) ++i1;
            else if (nums[i2] == minVal) ++i2;
            else ++i3;
        }

        cout << k1 << ' ' << k2 << ' ' << k3 << endl;
        return diff;
    }
};