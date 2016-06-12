import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/11/16.
 * Write a function to generate the generalized abbreviations of a word.
 *
 * Example:
 *
 * Given word = "word", return the following list (order does not matter):
 *
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d",
 * "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

public class GeneralizedAbbreviation {

    public static void main(String[] args) {
        GeneralizedAbbreviation app = new GeneralizedAbbreviation();
        String s = "word";
        List<String> abbreviations = app.generateAbbreviations(s);
        // iteration
        for (String str : abbreviations) {
            System.out.print(str + " ");
        }
        System.out.println();

        // recursion
        abbreviations = app.genrateAbbreviationsRecursion(s);
        for (String str : abbreviations) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    /**
     * If we use binary numbers to represent abbreviations, where 1
     * means the letter is missing, and 0 means the letter is kept.
     * For example, 0110-->w2d, 1010-->1o1d. There will be 2^(s.length())
     * abbreviations, from 0 to 2^(s.length()), each integer represents
     * an abbreviation. We count consecutive 1's and replace the letter(s)
     * with numbers.
     * @param s
     * @return
     */
    public List<String> generateAbbreviations(String s) {
        List<String> abbreviations = new ArrayList<>();
        if (null == s || s.isEmpty()) return abbreviations;

        int total = 1 << s.length();

        for (int i = 0; i < total; ++i) {
            int index = 0;
            int count = 0; // count of consecutive 1's
            StringBuilder str = new StringBuilder();
            while (index < s.length()) {
                if ((i & (1 << s.length()-1-index)) > 0) {
                    ++count;
                } else {
                    if (count > 0) {
                        str.append(Integer.toString(count));
                        count = 0;
                    }
                    str.append(s.charAt(index));
                }
                ++index;
            }
            if (count > 0) {
                str.append(Integer.toString(count));
            }
            abbreviations.add(str.toString());
        }

        return abbreviations;
    }

    public List<String> genrateAbbreviationsRecursion(String s) {
        List<String> abbreviations = new ArrayList<>();
        if (null == s || s.isEmpty()) return abbreviations;
        generateAbbreviations(s, 0, 0, "", abbreviations);
        return abbreviations;
    }

    private void generateAbbreviations(String word, int pos, int count,
                                       String abbreviation, List<String> abbreviations) {
        if (word.length() == pos) {
            if (count > 0) {
                abbreviation += Integer.toString(count);
            }
            abbreviations.add(abbreviation);
            return;
        }
        generateAbbreviations(word, pos+1, count+1, abbreviation, abbreviations);
        generateAbbreviations(word, pos+1, 0,
                abbreviation + (count > 0? Integer.toString(count) : "") + word.substring(pos, pos+1),
                abbreviations);
    }
}
