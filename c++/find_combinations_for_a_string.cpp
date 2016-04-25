/*
A string consists of ‘0’, ‘1’ and '?'. The question mark can be either '0' or '1'.
Find all possible combinations for a string.
01?0
-->0100, 0110
*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
	vector<string> findCombinations(const string& str) {
		vector<string> rs;
		if (str.empty()) {
			return rs;
		}

		findCombinations(str, rs, "");
		return rs;
	}

	void findCombinations(const string& str, vector<string>& rs, const string& curStr) {
		if (curStr.size() == str.size()) {
			rs.push_back(curStr);
			return;
		}

		int pos = curStr.size();
		if (str[pos] == '?') {
			findCombinations(str, rs, curStr+'0');
			findCombinations(str, rs, curStr+'1');
		} else {
			findCombinations(str, rs, curStr+str[pos]);
		}
	}
};

int main()
{
	string str = "11?0";
	Solution sol;
	vector<string> rs = sol.findCombinations(str);
	for (const string& str : rs) {
		cout << str << endl;
	}
	return 0;
}
