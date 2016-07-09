/*
The API: int read4(char *buf) reads 4
characters at a time from a file.
The return value is the actual number
of characters read. For example, it returns
3 if there is only 3 characters left in the file.
By using the read4 API, implement the function
int read(char *buf, int n) that reads n
characters from the file.
Note:
The read function will only be called once for each test case.
*/

int read4(char* buf);

class Solution {
public:
    int read(char* buf, int n) {
        int len = 0;
        int m = INT_MAX;
        while (len + 4 <= n) {
            m = read4(buf+len);
            len += m;
            if (m < 4) break;
        }

        // len == n indicates len%4 == 0
        // m < 4 means reader has reached eof
        if (len == n || m < 4) return len;

        // call read4 one more time and save
        // the remaining chars into buf one by one
        // Two situations:
        // 1. There are less chars remaining in the file than n-len
        // 2. There are more chars remaining in the file than n-len
        char* remain = new char[4];
        m = min(read4(remain), n-len);
        for (int i = 0; i < m; ++i) {
            buf[len++] = remain[i];
        }
        delete[] remain;
        return len;
    }
};