import java.util.*;

/**
 * Created on 6/30/16.
 * Given a non-empty string str and an integer k,
 * rearrange the string such that the same characters
 * are at least distance k from each other.
 *
 * All input strings are given in lowercase letters.
 * If it is not possible to rearrange the string,
 * return an empty string "".
 *
 * Example 1:
 * str = "aabbcc", k = 3
 *
 * Result: "abcabc"
 *
 * The same letters are at least distance 3 from each other.
 * Example 2:
 * str = "aaabc", k = 3
 *
 * Answer: ""
 *
 * It is not possible to rearrange the string.
 * Example 3:
 * str = "aaadbbcc", k = 2
 *
 * Answer: "abacabcd"
 *
 * Another possible answer is: "abcabcda"
 *
 * The same letters are at least distance 2 from each other.
 */

public class RearrangeStringKDistanceApart {

    public static void main(String[] args) {
        String str = "aaabc";
        int k = 3;
        RearrangeStringKDistanceApart app = new RearrangeStringKDistanceApart();
        System.out.println(app.rearrangeString(str, k));
    }

    private static class FrequencyCharPair {
        public FrequencyCharPair(int f, char c) {
            this.f = f;
            this.c = c;
        }
        public int f;
        public char c;
    }

    private class MyComparator implements Comparator<FrequencyCharPair> {
        public int compare(FrequencyCharPair f1, FrequencyCharPair f2) {
            if (f2.f == f1.f) return f1.c - f2.c;
            return f2.f - f1.f;
        }
    }

    /**
     * The idea is greedy. Build the char frequency map.
     * Put the pair<frequency, char> into the priority queue (max heap)
     * based on frequency and char relation.
     * For each round, get k (or less if the remaining chars are less than k)
     * chars from the top of priority queue and put the chars one by one
     * into the string builder. If the popped char's frequency is still greater
     * than 0 after deducted by 1, put the pair back into the
     * priority queue to repeat the process.
     * @param str
     * @param k
     * @return
     */
    public String rearrangeString(String str, int k) {
        if (k == 0) return "";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            if (!frequencyMap.containsKey(str.charAt(i))) {
                frequencyMap.put(str.charAt(i), 1);
            } else {
                frequencyMap.put(str.charAt(i), frequencyMap.get(str.charAt(i))+1);
            }
        }

        PriorityQueue<FrequencyCharPair> pq = new PriorityQueue<>(new MyComparator());
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new FrequencyCharPair(entry.getValue(), entry.getKey()));
        }

        StringBuilder strBuilder = new StringBuilder(str.length());

        int remainLen = str.length();
        while (!pq.isEmpty()) {
            List<FrequencyCharPair> candidates = new ArrayList<>();
            int cnt = Math.min(k, remainLen);
            for (int i = 0; i < cnt; ++i) {
                if (pq.isEmpty()) return "";
                FrequencyCharPair fcp = pq.poll();
                strBuilder.append(fcp.c);
                if (--fcp.f > 0) candidates.add(fcp);
                --remainLen;
            }
            for (FrequencyCharPair fcp : candidates) pq.offer(fcp);
        }

        return strBuilder.toString();
    }
}
