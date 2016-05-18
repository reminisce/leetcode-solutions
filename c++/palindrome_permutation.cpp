/*
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome.
How about character which occurs odd number of times?
*/

#include <string>
#include <unordered_set>
#include <iostream>

using namespace std;

class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_set<char> charSet;
        for (char c : s) {
            auto it = charSet.find(c);
            if (it == charSet.end()) {
                charSet.insert(c);
            } else {
                charSet.erase(it);
            }
        }

        return charSet.size() <= 1;
    }
};

int main()
{
    string s = "level";
    Solution sol;
    bool ret = sol.canPermutePalindrome(s);
    cout << s << (ret? " is" : " is not") << " permutably palindromic" << endl;
    return 0;
}
