/*
Given a string, determine if it is a palindrome,
considering only alphanumeric characters
and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be
empty? This is a good question to ask during an interview.

For the purpose of this problem, we define
empty string as valid palindrome.
*/

class Solution {
public:
    bool isPalindrome(string s) {
        if (s.empty()) return true;
        int left = 0, right = (int)s.size() - 1;
        while (left < right) {
            if (!isAlphanumeric(s[left])) ++left;
            else if (!isAlphanumeric(s[right])) --right;
            else if (!isEqualChars(s[left++], s[right--])) {
                return false;
            }
        }
        return true;
    }

    bool isEqualChars(char c1, char c2) {
        if (isDigit(c1) && isDigit(c2)) {
            return c1 == c2;
        } else if (isDigit(c1) || isDigit(c2)) {
            return false;
        }

        int i1 = isLowerCaseChar(c1) ? c1 - 'a' : c1 - 'A';
        int i2 = isLowerCaseChar(c2) ? c2 - 'a' : c2 - 'A';
        return i1 == i2;
    }

    bool isAlphanumeric(char c) {
        return isDigit(c) || isLowerCaseChar(c) || isUpperCaseChar(c);
    }

    bool isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    bool isLowerCaseChar(char c) {
        return c >= 'a' && c <= 'z';
    }

    bool isUpperCaseChar(char c) {
        return c >= 'A' && c <= 'Z';
    }
};