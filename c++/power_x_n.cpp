/*Implement pow(x, n).*/

#include <iostream>

using namespace std;

class Solution {
public:
    double myPow(double x, int n) {
        if (x == 0) return 0;

        if (n == 0) return 1;

        // If n = -2147483648, i.e. 0x8000000000000000
        // n = -n. This is overflow.
        long int pn = n;
        if (pn < 0) {
            x = 1.0 / x;
            pn = -pn;
        }

        double y = x;
        double res = 1.0;
        while (pn > 0) {
            if (pn % 2 == 1) {
                res *= y;
            }
            y *= y;
            pn /= 2;
        }

        return res;
    }
};

int main()
{
    double x = 2.0;
    int n = -2147483648;
    Solution sol;
    double p = sol.myPow(x, n);
    cout << p << endl;
    return 0;
}
