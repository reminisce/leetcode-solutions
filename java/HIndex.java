import java.util.Arrays;

/**
 * Created on 5/20/16.
 */
public class HIndex {
    /**
     * Time O(n)
     * Space O(n)
     * Use bucket sort to record the number of papers in each bucket (citation number).
     * The citation greater than N will be considered with N citations. Counting number
     * of papers from high to low citations till number of papers is greater than
     * the current bucket index i, return i.
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n+1];
        for (int citation : citations) {
            if (citation >= n) {
                ++bucket[n];
            } else {
                ++bucket[citation];
            }
        }

        int numPapers = 0;
        for (int i = n; i >= 0; --i) {
            numPapers += bucket[i];
            if (numPapers >= i) {
                return i;
            }
        }

        return 0;
    }

    /**
     * If the array citations is sorted in ascending order,
     * solve the problem in O(logn) time complexity.
     * Analysis: if the array is in descending order, we need
     * to find the first i such that citations[i] < i + 1.
     * So in ascending order, it's the first i such that
     * citations[i] < len - i, and return len - i.
     */
    public int hIndexII(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == n - mid) return n - mid;
            else if (citations[mid] > n - mid) right = mid - 1;
            else left = mid + 1;
        }
        return n - left;
    }

    /**
     * Time O(nlogn)
     * Space O(1)
     * Sort citations from high to low and check whether
     * citations[i] >= i+1 one by one.
     * If not, return i.
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        Arrays.sort(citations); // ascending order
        // reverse order
        for (int i = 0; i < citations.length / 2; ++i) {
            swap(citations, i, citations.length-1-i);
        }

        for (int i = 0; i < citations.length; ++i) {
            if (citations[i] < i + 1) {
                return i;
            }
        }

        return citations.length;
    }

    private void swap(int[] citations, int i, int j) {
        if (citations == null) return;
        int temp = citations[i];
        citations[i] = citations[j];
        citations[j] = temp;
    }
}
