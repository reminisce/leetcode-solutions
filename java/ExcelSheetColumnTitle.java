/**
 * Created on 8/3/16.
 * Given a positive integer, return its
 * corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */

public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        ExcelSheetColumnTitle app = new ExcelSheetColumnTitle();
        System.out.println(app.convertToTitle(28));
    }

    public String convertToTitle(int n) {
        if (n == 0) return "";
        StringBuilder str = new StringBuilder();
        while (n > 0) {
            str.append((char)((--n) % 26 + 'A'));
            n /= 26;
        }
        return str.reverse().toString();
    }
}
