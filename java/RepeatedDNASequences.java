import java.util.*;

/**
 * Created on 8/3/16.
 * All DNA is composed of a series of nucleotides
 * abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long
 sequences (substrings) that occur more than once
 in a DNA molecule.

 For example,

 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].
 */


public class RepeatedDNASequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        RepeatedDNASequences app = new RepeatedDNASequences();
        List<String> res = app.findRepeatedDnaSequences(s);
        System.out.println(res.toString());
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length() - 9; ++i) {
            String str = s.substring(i, i+10);
            if (map.containsKey(str)) {
                if (map.get(str) == 1) {
                    map.put(str, 2);
                    res.add(str);
                }
            } else {
                map.put(str, 1);
            }
        }
        return res;
    }
}
