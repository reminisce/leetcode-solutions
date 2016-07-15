/**
 * Created on 7/14/16.
 * Given an integer, convert it to a roman numeral.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * I = 1;
 * V = 5;
 * X = 10;
 * L = 50;
 * C = 100;
 * D = 500;
 * M = 1000;
 */

public class IntegerToRoman {

    public static void main(String[] args) {
        IntegerToRoman app = new IntegerToRoman();
        int num = 3542;
        System.out.println(num + " = " + app.intToRoman(num));
    }

    public String intToRoman(int num) {
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0;
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            int times = num / value[i];
            while (times-- > 0) {
                num -= value[i];
                str.append(symbol[i]);
            }
            ++i;
        }
        return str.toString();
    }
}
