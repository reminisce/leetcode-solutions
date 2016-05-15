/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: you may assume that n is not less than 2.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

n = 2: 1 + 1
n = 3: 1 + 2
n = 4: 2 + 2
n = 5: 2 + 3
n = 6: 3 + 3
n = 7: 3 + 2 + 2
n = 8: 3 + 3 + 2
n = 9: 3 + 3 + 3
n = 10: 3 + 3 + 2 + 2

So for any number greater than 4, we need to break the integer into most of 3's until the remaining number
is 2 or 4.
*/

class Solution {
public:
    int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
};