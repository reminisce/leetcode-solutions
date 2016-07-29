/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/

class Solution {
public:
    int mySqrt(int x) {
        if (x < 1) return 0;
        if (x == 1) return 1;
        int low = 1, high = x / 2 + 1;
        while (low <= high) {
            long int mid = low + (high - low) / 2;
            long int mid2 = mid * mid;
            if (mid2 == x) return mid;
            if (mid2 > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
};