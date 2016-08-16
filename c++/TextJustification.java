import java.util.LinkedList;
import java.util.List;

/**
 * Created on 8/15/16.
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"xxx", "xxxxxx", "x", "xxxxxxx", "xxxx", "xxxx", "xxxxx", "xx", "xxxxxx"};
        int L = 11;
        TextJustification app = new TextJustification();
        List<String> strs = app.fullJustify(words, L);
        for (String str : strs) {
            System.out.println(str);
        }
    }

    public List<String> fullJustify(String[] words, int L) {
        List<String> list = new LinkedList<>();

        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }

            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) { // not a single word, not last line
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            // for single word and last line
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }

        return list;
    }
}
