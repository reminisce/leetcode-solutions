import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created on 5/22/16.
 * Consider an Android phone password keypad as the following.
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * Given a length L, calculate how many patterns with that length.
 * Note: two numbers can be connected only when there are no
 * unused numbers on the path connecting them. For example,
 * 1 can be connected with 6, but not 9, because 5 is in the middle.
 * But if 5 has been used, 1 and 9 can be connected.
 */

public class AndroidPhoneUnlockPattern {

    public static void main(String[] args) {
        AndroidPhoneUnlockPattern app = new AndroidPhoneUnlockPattern();
        ArrayList<ArrayList<Integer>> patterns = app.getPatterns(3);
        for (ArrayList<Integer> pattern : patterns) {
            System.out.println(pattern.toString());
        }
    }

    public ArrayList<ArrayList<Integer>> getPatterns(int length) {
        ArrayList<ArrayList<Integer>> patterns = new ArrayList<>();
        ArrayList<Integer> pattern = new ArrayList<>(length);
        for (int i = 1; i <= length; ++i) pattern.add(i);
        HashSet<Integer> visited = new HashSet<>();
        getPatterns(0, 0, visited, pattern, patterns);
        return patterns;
    }

    private void getPatterns(int curKey,
                             int index,
                             HashSet<Integer> visited,
                             ArrayList<Integer> pattern,
                             ArrayList<ArrayList<Integer>> patterns) {
        if (index == pattern.size()) {
            patterns.add(new ArrayList<>(pattern));
            return;
        }

        for (int i = 1; i <= 9; ++i) {
            if (visited.contains(i)) continue;
            else if (curKey == 1) {
                if (i == 3 && !visited.contains(2)) continue;
                else if (i == 7 && !visited.contains(4)) continue;
                else if (i == 9 && !visited.contains(5)) continue;
            } else if (curKey == 2) {
                if (i == 8 && !visited.contains(5)) continue;
            } else if (curKey == 3) {
                if (i == 1 && !visited.contains(2)) continue;
                else if (i == 9 && !visited.contains(6)) continue;
                else if (i == 7 && !visited.contains(5)) continue;
            } else if (curKey == 4) {
                if (i == 6 && !visited.contains(5)) continue;
            } else if (curKey == 6) {
                if (i == 4 && !visited.contains(5)) continue;
            } else if (curKey == 7) {
                if (i == 1 && !visited.contains(4)) continue;
                else if (i == 9 && !visited.contains(8)) continue;
                else if (i == 3 && !visited.contains(5)) continue;
            } else if (curKey == 8) {
                if (i == 2 && !visited.contains(5)) continue;
            } else if (curKey == 9) {
                if (i == 3 && !visited.contains(6)) continue;
                else if (i == 7 && !visited.contains(8)) continue;
                else if (i == 1 && !visited.contains(5)) continue;
            }
            pattern.set(index, i);
            visited.add(i);
            getPatterns(i, index+1, visited, pattern, patterns);
            visited.remove(i);
        }
    }
}
