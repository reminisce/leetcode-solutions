/**
 * Created on 7/13/16.
 * Given a string, find the number of chunks of a chunk palindrome.
 * For example, abccba is a palindrome, with at most 6 chunks.
 * volvo is a chunk palindrome as (vo)(l)(vo), with at most 3 chunks.
 */
public class ChunkPalindrome {

    public static void main(String[] args) {
        String s = "volvo";
        ChunkPalindrome app = new ChunkPalindrome();
        System.out.println(app.countChunk(s));
    }

    public int countChunk(String s) {
        if (null == s) return 0;
        if (s.length() == 0 || s.length() == 1) return s.length();

        for (int i = 0; i < s.length() / 2; ++i) {
            String left = s.substring(0, i+1);
            String right = s.substring(s.length()-i-1);
            if (left.equals(right)) {
                return 2 + countChunk(s.substring(i+1, s.length()-i-1));
            }
        }
        return 1;
    }
}
