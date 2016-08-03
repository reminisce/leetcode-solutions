/**
 * Given a vector of n elements,
 * choose k elements (k < n) from
 * it such that every element in
 * the vector is considered uniformly.
 */

#include <vector>
#include <random>

using namespace std;

class ReserviorSampling {
public:
    /**
     * Basic idea is create a
     * vector of size k (reservior),
     * and put the first k elements
     * from the original vector into it.
     * Starting from index i = k,...,n-1,
     * each time generate a random number
     * within [0, i]. If the generated
     * random number is less than k,
     * the number indexed by the random
     * number in the reservior and the
     * i-th number.
     * Proof: We want to prove that at
     * each round i = k,...,n-1, every number
     * has k/(i+1) probability to stay
     * in the reservior.
     * For the specific case, consider i = k.
     * 1. The generated random number has k/(k+1)
     * probability to be in the reservior, which
     * means nums[k] has k/(k+1) to be selected
     * into the reservior.
     * 2. For the number already in the reservior,
     * say nums[j], 0<=j<=k-1, the chance of being
     * swapped out by nums[k] is the product
     * of the chance of nums[k] being selected
     * k/(k+1) and the chance of nums[j] being
     * selected among k numbers in the reservior 1/k,
     * that is k/(k+1) * 1/k = 1/(k+1). So the chance
     * of nums[j] survives in round k in the reservior
     * is 1 - 1/(k+1) = k/(k+1).
     * To generalize, in round i > k-1, the number
     * from 0,...,i survives in the reservior is
     * k/(i+1). We need to prove it is correct for
     * round i+1.
     * 1. The chance of nums[i+1] is selected into
     * the reservior is k/(i+2).
     * 2. The chance of a number nums[j] in the reservior
     * to be swapped out is the product of its being selected
     * selected in round i+1 (1/k), and nums[i+1] is
     * selected k/(i+2),
     * that is 1/k * k/(i+2) = 1/(i+2). It's survival
     * probability in this round given the fact
     * that it survived in the previous round
     * (conditional probability) is
     * 1 - 1/(i+2) = (i+1)/(i+2).
     * It's survival chance in the previous round is
     * k/(i+1). So the total survival chance
     * without condition is k/(i+1) * (i+1)/(i+2) = k/(i+2).
     */
    vector<int> sample(const vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> res(k);
        for (size_t i = 0; i < n && i < k; ++i) {
            res[i] = nums[i];
        }
        if (k >= n) return res;
        for (int i = k; i < n; ++i) {
            int j = rand()%(i+1);
            if (j < k) {
                res[j] = nums[i];
            }
        }
        return res;
    }
};
