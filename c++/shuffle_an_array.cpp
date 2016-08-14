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
    /**
     * We want to prove that for the i-th element, it has
     * 1/n probability to go to position 0, 1, 2,...,n-1.
     * We do it by induction. For n = 1, this is obviously
     * correct. Assume it's correct for n, and we want
     * to prove it's also correct for n+1.
     * We consider two situations:
     * 1. i = n, the index of the last element.
     * According to the algorithm, it has n/(n+1) chance
     * to be swapped out of index = n, and 1/n chance
     * to go to any position from 0 to n-1. So the probability
     * of it going to position i = 0, ..., n-1 is 1/n * n/(n+1) = 1/(n+1).
     * It is easy to know that it has 1/(n+1) chance to stay unchanged.
     * 2. i = 0,...,n-1. It's easy to know that each one of them
     * has chance 1/n to be picked and n/(n+1) chance to go to position n
     * after being picked. So the probability of each one
     * of them going to the last position is 1/(n+1).
     * Now we need to prove that each one of them has the same
     * chance to go to position 0,...,n-1. From the assumption,
     * we know that they have 1/n chance to go to position
     * 0,...,n-1 if they are not chosen to be swapped with element at n
     * whose chance is n/(n+1). So the total probability is 1/(n+1).
     * Done.
     */
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