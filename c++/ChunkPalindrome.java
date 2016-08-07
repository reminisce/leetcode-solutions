/**
 * Created on 7/13/16.
 * Given a string, find the number of chunks of a chunk palindrome.
 * For example, abccba is a palindrome, with at most 6 chunks.
 * volvo is a chunk palindrome as (vo)(l)(vo), with at most 3 chunks.
 */
public class ChunkPalindrome {

    public static void main(String[] args) {
        String s = "aabba";
        ChunkPalindrome app = new ChunkPalindrome();
        System.out.println("Recursive: " + app.countChunkRecursive(s));
        System.out.println("Iterative: " + app.countChunkIterative(s));
    }

    public int countChunkRecursive(String s) {
        if (null == s) return 0;
        if (s.length() == 0 || s.length() == 1) return s.length();

        for (int i = 0; i < s.length() / 2; ++i) {
            String left = s.substring(0, i+1);
            String right = s.substring(s.length()-i-1);
            if (left.equals(right)) {
                return 2 + countChunkRecursive(s.substring(i+1, s.length()-i-1));
            }
        }
        return 1;
    }

    public int countChunkIterative(String s) {
        if (null == s) return 0;
        int left = 0, right = s.length() - 1;
        int count = 0;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                count += 2;
                ++left;
                --right;
            } else {
                int i = left + 1, j = right - 1;
                while (i < j) {
                    if (s.substring(left, i+1).equals(s.substring(j, right+1))) {
                        count += 2;
                        left = i + 1;
                        right = j - 1;
                        break;
                    } else {
                        ++i;
                        --j;
                    }
                }
                if (i >= j) {
                    ++count;
                    break;
                }
            }
        }
        if (left == right) ++count;
        return count;
    }
}
