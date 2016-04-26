/*
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/

#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    int titleToNumber(string s) {
        if (s.empty()) return 0;

        int num = (int)(s[0]-'A')+1;
        for (int i = 1; i < s.size(); ++i) {
            num = 26 * num + (int)(s[i]-'A')+1;
        }

        return num;
    }
};    

int main()
{
    string s = "ABA";
    Solution sol;
    int num = sol.titleToNumber(s);
    cout << "Column " << s << " number is " << num << endl;
    return 0;
}