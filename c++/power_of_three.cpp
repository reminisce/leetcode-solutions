#include <cmath>
#include <limits>
#include <iostream>

using namespace std;

class Solution {
public:
    bool isPowerOfThree(int n) {
        static const int max_three_power = log(numeric_limits<int>::max()) / log(3);
        static const int max_power_of_three = pow(3, max_three_power);
        return n > 0 && max_power_of_three % n == 0;
    }
};

int main()
{
    int n;
    cout << "Enter a number: ";
    cin >> n;
    Solution sol;
    bool ret = sol.isPowerOfThree(n);
    cout << "Number " << n << (ret? " is" : " is not") << " a power of three\n";
    return 0;
}
