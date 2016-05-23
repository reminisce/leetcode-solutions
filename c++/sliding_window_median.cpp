/**
 * Created on 5/22/16.
 * Given an array of n integer, and a moving window(size k), move the window at each
 * iteration from the start of the array, find the median of the element inside the window at each moving.
 *
 * For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]
 * At first the window is at the start of the array like this
 * [ | 1,2,7 | ,8,5] , return the median 2;
 * then the window move one step forward.
 * [1, | 2,7,8 | ,5], return the median 7;
 * then the window move one step forward again.
 * [1,2, | 7,8,5 | ], return the median 7;
 *
 */

#include <vector>
#include <set>
#include <iostream>

using namespace std;

/**
 * NOTE: This solution can only work if there are no duplicates in the given array.
 */
class SlidingWindonwMedian {
public:
    typedef set<int, less<int>> MinSet;
    typedef set<int, greater<int>> MaxSet;
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        vector<double> medians;
        MinSet minSet;
        MaxSet maxSet;

        for (int i = 0; i < k; ++i) {
            addNum2Window(minSet, maxSet, nums[i]);
        }
        saveMedian(medians, minSet, maxSet, k);

        for (int i = 1; i <= (int)nums.size()-k; ++i) {
            removeNumberFromWindow(minSet, maxSet, nums[i-1]);
            addNum2Window(minSet, maxSet, nums[i+k-1]);
            saveMedian(medians, minSet, maxSet, k);
        }

        return medians;
    }

    void addNum2Window(MinSet& minSet, MaxSet& maxSet, int num) {
        if (minSet.size() == maxSet.size()) {
            minSet.insert(num);
        } else {
            maxSet.insert(num);
        }
        rebalance(minSet, maxSet);
    }

    /**
     * minSet saves the greater half of the numbers,
     * maxSet saves the less half of the numbers.
     * If k is an odd number, minSet has one more number
     * than maxSet. If minSet.begin() is smaller than
     * maxSet.begin(), need to rebalance.
     */
    void rebalance(MinSet& minSet, MaxSet& maxSet) {
        if (!minSet.empty() && !maxSet.empty()) {
            if (*minSet.begin() < *maxSet.begin()) {
                int a = *minSet.begin();
                int b = *maxSet.begin();
                minSet.erase(minSet.begin());
                maxSet.erase(maxSet.begin());
                minSet.insert(b);
                maxSet.insert(a);
            }
        }
    }

    void saveMedian(vector<double>& medians, const MinSet& minSet, const MaxSet& maxSet, int k) {
        if (k & 1) {
            medians.push_back((double)(*minSet.begin()));
        } else {
            medians.push_back((double)(*minSet.begin()+*maxSet.begin())/2.0);
        }
    }

    void removeNumberFromWindow(MinSet& minSet, MaxSet& maxSet, int num) {
        auto it1 = minSet.find(num);
        if (it1 != minSet.end()) {
            minSet.erase(it1);
            if (minSet.size() < maxSet.size()) {
                minSet.insert(*maxSet.begin());
                maxSet.erase(maxSet.begin());
            }
        } else {
            maxSet.erase(num);
            if (minSet.size() > maxSet.size() + 1) {
                maxSet.insert(*minSet.begin());
                minSet.erase(minSet.begin());
            }
        }
    }
};

int main()
{
    vector<int> nums = {1, 3, 4, 7, 5, 2, 8};
    int k = 3;
    SlidingWindonwMedian swm;
    vector<double> medians = swm.medianSlidingWindow(nums, k);
    for (int i = 0; i <= (int)nums.size() - k; ++i) {
        cout << "Sliding windown [";
        for (int j = i; j < i+k; ++j) {
            cout << nums[j] << ' ';
        }
        cout << "] median = " << medians[i] << endl;
    }
    return 0;
}
