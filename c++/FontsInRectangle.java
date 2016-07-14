/**
 * Created on 7/13/16.
 * Given a rectangular area with width = w, height = h,
 * a font size range from minF->maxF, and two functions:
 * 1. height_of(font) returning the height of a font
 * 2. width_of(font, ch) returning the width of ch in font,
 * find the greatest font size of a given string that
 * can fit in the rectangle.
 */

public class FontsInRectangle {

    public int maxFont(int w, int h, int minFont, int maxFont, String s) {
        // minFont cannot fit in the window, return 0
        if (!canFit(w, h, minFont, s)) return 0;

        int low = minFont, high = maxFont;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canFit(w, h, mid, s)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean canFit(int w, int h, int font, String s) {
        int maxLines = h / height_of(font);
        if (maxLines == 0) return false;
        int numLines = 0; // number of lines of s with font in this window
        int i = 0;
        while (i < s.length()) {
            int j = i;
            int curWidth = 0;
            while (j < s.length() && curWidth + width_of(font, s.charAt(j)) < w) {
                curWidth += width_of(font, s.charAt(j++));
            }
            if (j == s.length()) return true;
            ++numLines;
            if (numLines > maxLines) return false;
            i = j;
        }
        return true;
    }

    public int height_of(int font) {
        return 1;
    }

    public int width_of(int font, char c) {
        return 1;
    }
}
