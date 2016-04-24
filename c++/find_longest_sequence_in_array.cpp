/*
Given an array of integers, find the longest consecutive sequences in it.
For example, [5, 2, 3, 4, 5, 8, 6] should return 4 as [2, 3, 4, 5] is the longest consecutive sequence.
Time complexity: O(N)
Space complexity: O(1)
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
	// Loop through the array, for each element, count the longest
	// consecutive sequence ending at that element.
	int findLongestConsecutiveSequence(const vector<int>& nums) {
		if (nums.size() <= 1) {
			return nums.size();
		}

		int maxLength = 1;
		int curLength = 1;
		for (int i = 1; i < nums.size(); ++i) {
			if (nums[i] == nums[i-1] + 1) {
				++curLength;
			} else {
				curLength = 1;
			}
			maxLength = max(curLength, maxLength);
		}

		return maxLength;
	}
};

int main()
{
	vector<int> nums = {5, 2, 3, 4, 5, 8, 6};
	Solution sol;
	int length = sol.findLongestConsecutiveSequence(nums);
	cout << "max length = " << length << endl;
	return 0;
}