/* Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times. You may assume that the
 * array is non-empty and the majority element always exist in the array.
 */

#include <cstdlib>
#include <vector>

using namespace std;

class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int candidate = nums[0];
        int count = 1;
        for (size_t i = 1; i < nums.size(); ++i) {
            if (nums[i] == candidate) {
                ++count;
            }
            else if (count > 0) {
                --count;
            }
            
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }
        
        return candidate;
    }
};

int main(int argc, char** argv) {

    return 0;
}

