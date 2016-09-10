import java.util.ArrayList;

/**
 * Created on 5/22/16.
 * Given an integer N > 0, consider dividing the set {1, 2,..., N} into two subsets
 * such that their intersection is empty and their union is the whole set, and they have
 * the same sum.
 */
public class SubsetsWithEqualSum {
    public static void main(String[] args) {
        SubsetsWithEqualSum app = new SubsetsWithEqualSum();
        int n = 7;
        ArrayList<ArrayList<Integer>> subsets = app.getEqualSumSets(8);
        for (ArrayList<Integer> list : subsets) {
            System.out.println(list.toString());
        }
    }

    /**
     * Caculate the half sum first, and then it becomes a combination sum problem.
     * Apply greedy algorithm by taking the largest element that is
     * less than the remaining sum first until it reaches
     * the half sum.
     * @param n
     * @return
     */
    public ArrayList<ArrayList<Integer>> getEqualSumSets(int n) {
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
        int sum = n * (n + 1) / 2;
        if ((sum & 1) > 0) {
            return subsets;
        }
        sum /= 2;
        ArrayList<Integer> subset1 = new ArrayList<>();
        ArrayList<Integer> subset2 = new ArrayList<>();
        subsets.add(subset1);
        subsets.add(subset2);
        for (int i = n; i >= 1; --i) {
            if (sum >= i) {
                sum -= i;
                subset1.add(i);
            } else {
                subset2.add(i);
            }
        }

        return subsets;
    }
}
