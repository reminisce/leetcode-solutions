#include <vector>
#include <random>

using namespace std;

class Solution {
public:
    Solution(vector<int> nums) {
        originalNums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    vector<int> reset() {
        return originalNums;
    }
    
    /** Returns a random shuffling of the array. */
    vector<int> shuffle() {
        vector<int> shuffledNums = originalNums;
        for (int i = 1; i < (int)shuffledNums.size(); ++i) {
            int j = rand() % (i+1);
            if (j != i) {
                swap(shuffledNums[i], shuffledNums[j]);
            }
        }
        return shuffledNums;
    }

private:
    vector<int> originalNums;
};