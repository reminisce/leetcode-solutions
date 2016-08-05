/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous.
You should gather all requirements up front before implementing one.
*/

#include <string>

using namespace std;

class Solution {
public:
    enum InputType {
        INVALID,
        SPACE,
        SIGN,
        DIGIT,
        DOT,
        EXPONENT
    };

    const int stateTransTable[9][6] = {
    //INVAL  SPACE SIGN DIGIT DOT EXPONENT
        {-1,   0,   3,   1,   2,   -1}, // no input or only space input
        {-1,   8,  -1,   1,   4,    5}, // after entering a digit
        {-1,  -1,  -1,   4,  -1,   -1}, // no digits, only a dot so far
        {-1,  -1,  -1,   1,   2,   -1}, // after entering a sign
        {-1,   8,  -1,   4,  -1,    5}, // has digists and a dot so far
        {-1,  -1,   6,   7,  -1,   -1}, // after entering 'e' or 'E'
        {-1,  -1,  -1,   7,  -1,   -1}, // enter a sign after entering 'e' or 'E'
        {-1,   8,  -1,   7,  -1,   -1}, // enter a digit after entering 'e' or 'E'
        {-1,   8,  -1,  -1,  -1,   -1}  // enter a space after entering valid digits
    };

    bool isNumber(const string& s) {
        int state = 0;
        for (int i = 0; i < s.size(); ++i) {
            InputType input = INVALID;
            if (s[i] == ' ') input = SPACE;
            else if (s[i] == '+' || s[i] == '-') input = SIGN;
            else if (s[i] >= '0' && s[i] <= '9') input = DIGIT;
            else if (s[i] == '.') input = DOT;
            else if (s[i] == 'e' || s[i] == 'E') input = EXPONENT;
            state = stateTransTable[state][input];
            if (state == -1) return false;
        }
        return state == 1 || state == 4 || state == 7 || state == 8;
    }
};
