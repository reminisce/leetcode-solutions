/*
Given two arrays of length m and n with digits 0-9
representing two numbers. Create the maximum number
of length k <= m + n from digits of the two.
The relative order of the digits from the same
array must be preserved. Return an array of the
k digits. You should try to optimize your time
and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> maxNumber(vector<int>& nums1, vector<int>& nums2, int k) {
        int len1 = nums1.size();
        int len2 = nums2.size();
        vector<int> res;
        for (int i = 0; i <= k; ++i) {
            if (i > len1 || k-i > len2) continue;
            vector<int> res1, res2, tmp;
            maxArray(nums1, i, res1);
            maxArray(nums2, k-i, res2);
            merge(res1, res2, tmp);
            if (isGreater(tmp, 0, res, 0)) {
                res = tmp;
            }
        }
        return res;
    }

    void maxArray(const vector<int>& nums, int k, vector<int>& res) {
        if (k == 0) return;
        int n = nums.size();
        int drop = n - k; // indicates how many numbers should be dropped
        for (int num : nums) {
            while (drop > 0 && !res.empty() && res.back() < num) {
                res.pop_back();
                --drop;
            }
            res.push_back(num);
        }
        res.resize(k);
    }

    void merge(const vector<int>& nums1, const vector<int>& nums2, vector<int>& res) {
        size_t i = 0, j = 0;
        while (i < nums1.size() && j < nums2.size()) {
            if (isGreater(nums1, i, nums2, j)) {
                res.push_back(nums1[i++]);
            } else {
                res.push_back(nums2[j++]);
            }
        }

        while (i < nums1.size()) res.push_back(nums1[i++]);
        while (j < nums2.size()) res.push_back(nums2[j++]);
    }

    bool isGreater(const vector<int>& nums1, size_t idx1, const vector<int>& nums2, size_t idx2) {
        while (idx1 < nums1.size() && idx2 < nums2.size() && nums1[idx1] == nums2[idx2]) {
            ++idx1;
            ++idx2;
        }
        if (idx1 == nums1.size()) return false;
        if (idx2 == nums2.size()) return true;
        return nums1[idx1] > nums2[idx2];
    }
};

int main()
{
    vector<int> nums1 = {3, 4, 6, 5};
    vector<int> nums2 = {9, 1, 2, 5, 8, 3};
    int k = 5;
    Solution sol;
    vector<int> res = sol.maxNumber(nums1, nums2, k);
    for (int num : res) {
        cout << num << ' ';
    }
    cout << endl;
    return 0;
}
