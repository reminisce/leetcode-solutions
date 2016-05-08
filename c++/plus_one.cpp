/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {        
        int n = digits.size();
        vector<int> newDigits(n);
        int carry = 1;
        for (int i = n - 1; i >= 0; --i) {
            int sum = digits[i] + carry;
            newDigits[n-1-i] = sum % 10;
            carry = sum / 10;
            if (i == 0 && carry > 0) {
                newDigits.push_back(carry);
            }
        }

        reverse(newDigits.begin(), newDigits.end());
        return newDigits;
    }
};

int main()
{
    vector<int> digits = {9, 8, 0, 9};
    Solution sol;
    vector<int> newDigits = sol.plusOne(digits);
    for (int d : newDigits) {
        cout << d;
    }
    cout << endl;
    return 0;
}