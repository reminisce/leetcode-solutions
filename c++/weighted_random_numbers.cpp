/**
 * Given a number array
 * and each number's weight,
 * write a random number generator
 * based on the weight of the numbers.
 */

#include <vector>
#include <random>
#include <iostream>
#include <cassert>

using namespace std;

class WeightedNumberGenerator {
public:
    WeightedNumberGenerator(const vector<int>& nums, const vector<int>& weights)
    : m_nums(nums), m_weights(weights), m_totalWeights(0) {
        for (int w : weights) m_totalWeights += w;
    }

    int generate() const {
        int randomWeight = rand() % m_totalWeights;
        for (size_t i = 0; i < m_weights.size(); ++i) {
            if (randomWeight < m_weights[i]) {
                return m_nums[i];
            }
            randomWeight -= m_weights[i];
        }
        assert(false);
        return -1;
    }

private:
    const vector<int>& m_nums;
    const vector<int>& m_weights;
    int m_totalWeights;
};

int main()
{
    vector<int> nums = {1, 2, 3, 4};
    vector<int> weights = {4, 3, 2, 1};
    WeightedNumberGenerator generator(nums, weights);
    cout << generator.generate() << endl;
    return 0;
}
