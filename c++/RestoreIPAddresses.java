import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/23/16.
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 *
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

public class RestoreIPAddresses {

    public static void main(String[] args) {
        String s = "104303";
        RestoreIPAddresses app = new RestoreIPAddresses();
        List<String> res = app.restoreIpAddresses(s);
        System.out.println(s);
        for (String ip : res) {
            System.out.println(ip);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        String ip = "";
        restoreIpAddressesHelper(s, 0, 1, ip, res);
        return res;
    }

    private void restoreIpAddressesHelper(String s, int start, int section, String ip, ArrayList<String> res) {
        if (section == 4) {
            String substr = s.substring(start);
            if (isValid(substr)) {
                res.add(ip + substr);
            }
        }

        for (int i = start; i < start + 3 && i < s.length()-1; ++i) {
            String substr = s.substring(start, i+1);
            if (isValid(substr)) {
                restoreIpAddressesHelper(s, i+1, section+1, ip+substr+'.', res);
            }
        }
    }

    /**
     * Check if s can become one valid part of an ip address
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        if (s.length() == 0 || s.length() > 3) return false;
        if (s.length() > 1 && s.charAt(0) == '0') return false;
        if (Integer.parseInt(s) > 255) return false;
        return true;
    }
}
