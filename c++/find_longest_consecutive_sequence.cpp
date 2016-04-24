/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> numSet;
        for (int num : nums) {
            numSet.insert(num);
        }

        int maxLength = 1;
        while (!numSet.empty()) {
            int num = *(numSet.begin());
            numSet.erase(numSet.begin());

            int curLength = 1;
            int smallerNum = num - 1;
            while (!numSet.empty()) {
                auto it = numSet.find(smallerNum);
                if (it != numSet.end()) {
                    ++curLength;
                    --smallerNum;
                    numSet.erase(it);
                } else {
                    break;
                }
            }

            int largerNum = num + 1;
            while (!numSet.empty()) {
                auto it = numSet.find(largerNum);
                if (it != numSet.end()) {
                    ++curLength;
                    ++largerNum;
                    numSet.erase(it);
                } else {
                    break;
                }
            }

            maxLength = max(curLength, maxLength);
        }

        return maxLength;
    }
};

int main()
{
    vector<int> nums = {100, 4, 200, 1, 3, 2};
    Solution sol;
    int length = sol.longestConsecutive(nums);
    cout << "max length = " << length << endl;
    return 0;
}