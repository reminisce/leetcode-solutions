import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 8/20/16.
 * The API: int read4(char *buf)
 * reads 4 characters at a time from a file.
 * The return value is the actual number of
 * characters read. For example, it returns
 * 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the
 * function int read(char *buf, int n)
 * that reads n characters from the file.
 * Note: The read function may be called multiple times.
 */

public class ReadNCharactersGivenRead4II {

    public int read(char[] buf, int n) {
        int len = 0;
        while (!remain.isEmpty() && len < n) {
            buf[len++] = remain.poll();
        }
        if (len == n) return len;
        while (len < n) {
            char[] tmp = new char[4];
            int curLen = read4(tmp);
            if (len + curLen <= n) {
                for (int i = 0; i < curLen; ++i) {
                    buf[len++] = tmp[i];
                }
            } else {
                int remainLen = n - len;
                for (int i = 0; i < remainLen; ++i) {
                    buf[len++] = tmp[i];
                }
                for (int i = remainLen; i < curLen; ++i) {
                    remain.offer(tmp[i]);
                }
            }
            if (curLen < 4) return len;
        }
        return len;
    }

    private int read4(char[] buf) {
        return 4;
    }

    private Queue<Character> remain = new LinkedList<>();
}
