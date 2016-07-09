/*
The API: int read4(char *buf) reads 4
characters at a time from a file.

The return value is the actual number
of characters read. For example, it
returns 3 if there is only 3 characters
left in the file.

By using the read4 API, implement the
function int read(char *buf, int n) that
reads n characters from the file.

Note:
The read function may be called multiple times.
*/

int read4(char* buf);

class Solution {
public:
    int read(char* buf, int n) {
        int index = 0;
        while (index < n && !remain.empty()) {
            buf[index++] = remain.front();
            remain.pop();
        }

        char* tempBuf = new char[4];
        while (index < n) {
            int len = read4(tempBuf);
            if (len > n - index) {
                for (int k = 0; k < n - index; ++k) {
                    buf[index+k] = tempBuf[k];
                }
                for (int k = n - index; k < len; ++k) {
                    remain.push(tempBuf[k]);
                }
            } else {
                for (int k = 0; k < len; ++k) {
                    buf[index+k] = tempBuf[k];
                }
            }
            index += len;
            if (len < 4) return min(index, n);
        }
        return n;
    }

private:
    // buffer the chars remaining
    // from the last read
    queue<char> remain;
}