/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}
 

So Machine 1 does:

string encoded_string = encode(strs);
 

and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
 

strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

/**
 * Encode string by putting "number_of_length/" in front the string.
 * When decoding it, look for '/' and the number before it.
 * For example, "adfdas", "casdfsda" --> encoded into "6/adfdas8/casdfsda"
 */
class Codec {
public:
    // Encodes a list of strings to a single string.
    string encode(vector<string>& strs) {
        string str = "";
        for (string& s : strs) {
            str += to_string(s.length()) + '/' + s;
        }
        return str;
    }

    // Decodes a single string to a list of strings.
    vector<string> decode(string s) {
        vector<string> rs;
        int i = 0, j = 0;
        while (j < s.size()) {
            while (j < s.size() && s[j] != '/') {
                ++j;
            }
            if (j == s.size()) break;
            int len = stoi(s.substr(i, j - i));
            rs.push_back(s.substr(++j, len));
            j += len;
            i = j;
        }
        return rs;
    }
};

int main() {
    vector<string> strs;
    strs.push_back("dafasdf");
    strs.push_back("4/ads23");
    strs.push_back("29/adf/324////");
    Codec codec;
    string encodedStr = codec.encode(strs);
    cout << encodedStr << endl;

    vector<string> rs = codec.decode(encodedStr);
    for (string& str : rs) {
        cout << str << ' ';
    }
    cout << endl;
    return 0;
}