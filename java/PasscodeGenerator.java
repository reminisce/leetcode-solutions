import java.util.HashSet;
import java.util.Set;

/**
 * Created on 7/18/16.
 * Generate a string covering all the four digit
 * combinations between 0000 and 9999 both inclusive.
 * The string should be as short as possible. (G)
 */
public class PasscodeGenerator {

    public static void main(String[] args) {
        PasscodeGenerator app = new PasscodeGenerator();
        System.out.println(app.generate());
    }

    public String generate() {
        int start = 9999;
        String res = "" + start;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        while (visited.size() < 10000) {
            int next = start * 10 % 10000;
            int i = 0;
            for (; i <= 9; ++i) {
                if (!visited.contains(next+i)) break;
            }
            visited.add(next+i);
            start = next + i;
            res += i;
        }
        return res;
    }
}
