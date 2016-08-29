/*
http://qa.geeksforgeeks.org/3734/print-the-pretty-json-strings-facebook-microsoft
Given a json string, print the string
in a beautified format.
*/

#include <string>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<string> prettyPrint(string str) {
        vector<string> result;
        if (str.length() == 0) 
            return result;
     
        str.erase(remove(str.begin(), str.end(), ' '), str.end());
     
        string indent = "";
        string curr = "";
        int i = 0, len = str.length();
         
        while (i < len)
        {
            curr.push_back(str[i]);
            switch(str[i])
            {
                case ',':
                    result.push_back(curr);
                    curr = indent;
                    i++;
                    break;
     
                case ':':
                    i++;
                    if (str[i] == '{' || str[i] == '[')
                    {
                        result.push_back(curr);
                        curr = indent;
                    }
                    break;
     
                case '{':
                case '[':
     
                    i++;
                    result.push_back(curr);
                    if (i < len && (str[i] != '}' || str[i] != ']'))
                    {
                        indent.push_back('\t');
                    }
                    curr = indent;
                    break;
     
                case '}':
                case ']':
                    i++;
                    if (i < len && str[i] == ',') break;
                    result.push_back(curr);
                    if (i < len && (str[i] == '}' || str[i] == ']'))
                    {
                        if (!indent.empty()) indent.pop_back();
                    }
                    curr = indent;
                    break;
     
                default : 
                    i++;
                    if (i < len && (str[i] == '}' || str[i] == ']'))
                    {
                        result.push_back(curr);
                        if (!indent.empty()) indent.pop_back();
                        curr = indent;
                    }
            }
        }
        return result;
    }
};

int main()
{
    // string str = "{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";
    string str = "[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]";
    Solution sol;
    vector<string> prettyJson = sol.prettyPrint(str);
    cout << str << endl;
    for (auto& str : prettyJson) {
        cout << str << endl;
    }
    return 0;
}
