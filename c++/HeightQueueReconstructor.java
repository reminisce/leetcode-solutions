import java.util.Arrays;
import java.util.Comparator;

/**
 * Created on 6/12/16. (G)
 * Given an array of people instance, where height represents
 * the height of this person, and taller means the number of
 * people taller than this person in the queue. Reconstruct
 * the queue based on this array.
 */

public class HeightQueueReconstructor {

    public static void main(String[] args) {
        Person[] persons = new Person[4];
        persons[0] = new Person(5, 1);
        persons[1] = new Person(6, 0);
        persons[2] = new Person(4, 2);
        persons[3] = new Person(3, 1);
        HeightQueueReconstructor app = new HeightQueueReconstructor();
        System.out.println(Arrays.toString(app.reconstruct(persons)));
    }

    public static class Person {
        public Person(int height, int taller) {
            this.height = height;
            this.taller = taller;
        }

        public String toString() {
            return "{height:" + height + ", taller:" + taller + "}";
        }

        public int height;
        public int taller;
    }

    public Person[] reconstruct(Person[] persons) {
        Arrays.sort(persons, new PersonComparator());
        int n = persons.length;
        Person[] queue = new Person[n];
        for (int i = 0; i < n; ++i) {
            insert(queue, i, persons[i]);
        }
        return queue;
    }

    private void insert(Person[] persons, int curLength, Person person) {
        if (0 == curLength || person.taller >= curLength) {
            persons[curLength++] = person;
            return;
        }

        int idx = curLength++;
        while (idx > person.taller) {
            persons[idx] = persons[--idx];
        }
        persons[idx] = person;
    }

    private class PersonComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p2.height - p1.height;
        }
    }
}
