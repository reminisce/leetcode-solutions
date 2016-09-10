import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * (F)
 * Created on 8/29/16.
 * Given a series of tasks and cooldown time,
 * find the smallest total time needed to
 * execute all the tasks one by one.
 * For example, given 12323 and k = 3,
 * the total execution sequence should be
 * 123__23, that is 7.
 */

public class SchedulingTasks {

    public static void main(String[] args) {
        String s = "121";
        int k = 2;
        SchedulingTasks app = new SchedulingTasks();
        System.out.println(app.findTotalTime(s, k));
    }

    public int findTotalTime(String tasks, int cooldown) {
        int totalTime = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length(); ++i) {
            if (!map.containsKey(tasks.charAt(i))) {
                map.put(tasks.charAt(i),++totalTime);
            } else {
                int lastFinishTime = map.get(tasks.charAt(i));
                if (totalTime - lastFinishTime >= cooldown) {
                    map.put(tasks.charAt(i), ++totalTime);
                } else {
                    totalTime = cooldown + lastFinishTime + 1;
                    map.put(tasks.charAt(i), totalTime);
                }
            }
        }
        return totalTime;
    }
}
