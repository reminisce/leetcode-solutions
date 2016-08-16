import java.util.List;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Lock;

/**
 * Created on 8/15/16.
 * (L)
 */
public class BoundedBlockingQueue<E> {
    final Lock lock = new ReentrantLock();
    final Condition okToEnqueue = lock.newCondition();
    final Condition okToPoll = lock.newCondition();

    public BoundedBlockingQueue() {
        this.elements = null;
        this.count = 0;
        this.enqueueIdx = 0;
        this.currentIdx = -1;
    }

    public void init(int capacity) throws Exception {
        if (elements != null) {
            throw new IllegalStateException();
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        elements = new Object[capacity];
        count = 0;
        enqueueIdx = 0;
        currentIdx = -1;
    }

    public void enqueue(Object element) throws InterruptedException {
        lock.lock();
        try {
            while (count == elements.length) {
                okToEnqueue.await();
            }
            elements[enqueueIdx] = element;
            if (++enqueueIdx == elements.length) enqueueIdx = 0;
            ++count;
            okToPoll.signal();
        } finally {
            lock.unlock();
        }
    }

    public void multiEnqueue(List<E> objs) throws InterruptedException {
        lock.lock();
        try {
            if (objs.size() > elements.length) {
                throw new IllegalArgumentException();
            }

            while (objs.size() + count > elements.length) {
                okToEnqueue.await();
            }
            for (E obj : objs) {
                enqueue(obj);
            }
            okToPoll.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E poll() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                okToPoll.await();
            }
            Object obj = elements[currentIdx];
            if (++currentIdx == elements.length) currentIdx = 0;
            --count;
            okToEnqueue.signal();
            return (E) obj;
        } finally {
            lock.unlock();
        }
    }

    private Object[] elements;
    private int count; // for current number of elements
    private int enqueueIdx; // for circular buffer enqueueIdx index
    private int currentIdx; // for circular buffer current index
}
