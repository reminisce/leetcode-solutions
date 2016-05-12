/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    /**
     * Use a vector<string> to record chars of each row.
     * Use two loops of vector<string>:
     * 1. First loop: from 0 to nrow-1, push chars to each row, respectively.
     * 2. Second loop: from nrow-2 to 1, push chars to each row, respectively.
     */
    string convert(string s, int numRows) {
        vector<string> stringRows(numRows, "");
        int i = 0;
        while (i < s.size()) {
            for (int j = 0; j < numRows && i < s.size(); ++j) {
                stringRows[j].push_back(s[i++]);
            }
            for (int j = numRows-2; j >= 1 && i < s.size(); --j) {
                stringRows[j].push_back(s[i++]);
            }
        }

        string zigzagString;
        for (const string& str : stringRows) {
            zigzagString += str;
        }
        return zigzagString;
    }
};

int main()
{
    string s = "asdfdasfasdf";
    int numRows = 5;
    Solution sol;
    string zstring = sol.convert(s, numRows);
    return 0;
}
