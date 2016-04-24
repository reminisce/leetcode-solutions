/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<string> generateParenthesis(int n) {
    	vector<string> rs;
    	if (n <= 0) {
    		return rs;
    	}

    	string str;
    	str.resize(2*n);

    	generateParenthesis(n, n, rs, str, 0);
    	return rs;
    }

    void generateParenthesis(int left, int right, vector<string>& rs, string& str, int curIdx) {
    	if (left == 0 && right == 0) {
    		rs.push_back(str);
    		return;
    	}

    	if (left > right) {
    		return;
    	}

    	if (left > 0) {
    		str[curIdx] = '(';
	    	generateParenthesis(left-1, right, rs, str, curIdx+1);
	    }

	    if (right > 0 && right > left) {
	    	str[curIdx] = ')';
	    	generateParenthesis(left, right-1, rs, str, curIdx+1);
	    }
    }
};

int main()
{
	int k = 4;
	Solution sol;
	vector<string> rs = sol.generateParenthesis(k);
	for (const string& str : rs) {
		cout << str << endl;
	}
	return 0;
}