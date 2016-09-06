/*
Given an unsorted array of integers,
find the length of the longest
consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is
[1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

#include <iostream>
#include <unordered_set>
#include <vector>

using namespace std;

class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        if (nums.empty()) return 0;
        unordered_set<int> numSet;
        for (int num : nums) {
            numSet.insert(num);
        }

        int maxLen = 1;
        while (!numSet.empty()) {
            int seed = *numSet.begin();
            numSet.erase(numSet.begin());

            int curLen = 1;
            int smaller = seed - 1;
            auto it = numSet.find(smaller);
            while (it != numSet.end()) {
                ++curLen;
                numSet.erase(it);
                it = numSet.find(--smaller);
            }

            int greater = seed + 1;
            it = numSet.find(greater);
            while (it != numSet.end()) {
                ++curLen;
                numSet.erase(it);
                it = numSet.find(++greater);
            }

            maxLen = max(curLen, maxLen);
        }

        return maxLen;
    }
};

int main()
{
    vector<int> nums = {100, 4, 200, 1, 3, 2};
    Solution sol;
    cout << sol.longestConsecutive(nums) << endl;
    return 0;
}
