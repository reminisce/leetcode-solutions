/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

class Solution {
public:
    /**
     * Use two pointers to scan from begin and end to mid at the same time.
     * 1. If nums[l] = nums[r], it must be 0, 1, or, 8
     * 2. Else, it must be (6 and 9) or (9 and 6).
     */
    bool isStrobogrammatic(string num) {
        int l = 0, r = num.size() - 1;
        while (l <= r) {
            if (num[l] == num[r]) {
                if (num[l] != '1' && num[l] != '0' && num[l] != '8'){
                    return false;
                }
            } else {
                if ((num[l] != '6' || num[r] != '9') && (num[l] != '9' || num[r] != '6')) {
                    return false;
                }
            }
            ++l; --r;
        }
        return true;
    }
};