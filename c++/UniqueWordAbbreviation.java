import java.util.HashMap;
import java.util.HashSet;

/**
 * Created on 5/21/16.
 * An abbreviation of a word follows the form <first letter><number><last letter>.
 * Below are some examples of word abbreviations:
 *
 * a) it                      --> it    (no abbreviation)
 *
 *      1
 * b) d|o|g                   --> d1g
 *
 *               1    1  1
 *      1---5----0----5--8
 * c) i|nternationalizatio|n  --> i18n
 *
 *               1
 *      1---5----0
 * d) l|ocalizatio|n          --> l10n
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 * Example:
 *
 * Given dictionary = [ "deer", "door", "cake", "card" ]
 *
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */
public class UniqueWordAbbreviation {
    public static void main(String[] args) {
        String[] dictionary = {"dear", "door", "cake", "card"};
        UniqueWordAbbreviation uwa = new UniqueWordAbbreviation(dictionary);
        System.out.println("uwa.isUnique(\"dear\") = " + uwa.isUnique("dear"));
        System.out.println("uwa.isUnique(\"cart\") = " + uwa.isUnique("cart"));
        System.out.println("uwa.isUnique(\"cane\") = " + uwa.isUnique("cane"));
        System.out.println("uwa.isUnique(\"make\") = " + uwa.isUnique("make"));
    }

    public UniqueWordAbbreviation(String[] dictionary) {
        if (abbrev2StringsMap == null) {
            abbrev2StringsMap = new HashMap<>();
        }
        for (String word : dictionary) {
            String abbrev = toAbbreviation(word);
            if (!abbrev2StringsMap.containsKey(abbrev)) {
                abbrev2StringsMap.put(abbrev, new HashSet<>());
            }
            abbrev2StringsMap.get(abbrev).add(word);
        }
    }

    public boolean isUnique(String word) {
        if (abbrev2StringsMap == null) return true;
        String abbrev = toAbbreviation(word);
        if (!abbrev2StringsMap.containsKey(abbrev)) return true;
        return abbrev2StringsMap.get(abbrev).contains(abbrev) && abbrev2StringsMap.get(abbrev).size() == 1;
    }

    public String toAbbreviation(String word) {
        if (word.length() <= 2) return word; // no abbreviation
        return word.substring(0, 1) + (word.length() - 2) + word.substring(word.length()-1);
    }

    private HashMap<String, HashSet<String>> abbrev2StringsMap;
}

