/* Count the number of prime numbers less than a non-negative number, n. */

class Solution {
public:
    int countPrimes(int n) {
        if (n <= 2) return 0;
        bool* is_prime = new bool[n];
        for (int i = 0; i < n; ++i) is_prime[i] = true;
        for (int i = 2; i * i < n; ++i) {
            if (is_prime[i]) {
                for (int j = i; j * i < n; ++j) {
                    is_prime[j*i] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (is_prime[i]) ++count;
        }

        delete[] is_prime;
        return count;
    }
};