/**
 * (F)
 * Created on 9/4/16.
 * Given a integer, implement add one
 * without using "+".
 */
public class AddIntegerByOne {

    public static void main(String[] args) {
        AddIntegerByOne app = new AddIntegerByOne();
        for (int i = -9999; i <= 9999; ++i) {
            int diff = app.addOne(i) - (i+1);
            if (diff != 0) {
                System.out.println("addOne(" + i + ") = " + app.addOne(i));
            }
        }
    }

    public int addOne(int num) {
        int mask = Integer.MAX_VALUE;
        int carry = 1;
        while ((num & carry) != 0) {
            mask <<= 1;
            num &= mask;
            carry <<= 1;
        }
        return num | carry;
    }
}
