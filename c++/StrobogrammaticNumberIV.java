import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/21/16.
 * Given a positive integer n, output all the strobogrammatic numbers whose
 * length is not greater than n.
 */
public class StrobogrammaticNumberIV {
    public static void main(String[] args) {
        StrobogrammaticNumberIV app = new StrobogrammaticNumberIV();
        List<String> res = app.findStrobogrammatic(3);
        System.out.println(res.toString());
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        find(n, "0", res);
        find(n, "1", res);
        find(n, "8", res);
        find(n, "", res);
        return res;
    }

    private void find(int n, String str, List<String> res) {
        if (str.length() > n) return;
        if (str.length() == 1 || (!str.isEmpty() && str.charAt(0) != '0')) {
            res.add(str);
        }
        find(n, "0" + str + "0", res);
        find(n, "1" + str + "1", res);
        find(n, "6" + str + "9", res);
        find(n, "8" + str + "8", res);
        find(n, "9" + str + "6", res);
    }
}
