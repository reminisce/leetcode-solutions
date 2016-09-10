/**
 * Created on 8/11/16.
 * Implement a read write lock. (G)
 * Followup:
 * 1. Can readers starve?
 * 2. Why does checkRead need a while?
 * https://courses.cs.washington.edu/courses/cse451/12au/L8.pdf
 */

interface Lock {
    void acquire();
    void release();
}

interface Condition {
    void wait(Lock lock);
    void notifyOne(Lock lock);
    void notifyAll(Lock lock);
}

public class ReadWriteLock {
    public ReadWriteLock() {
        this.activeReaders = 0;
        this.activeWriters = 0;
        this.waitingReaders = 0;
        this.waitingWriters = 0;
        this.okToRead = null;
        this.okToWrite = null;
        this.lock = null;
    }

    public void read() {
        // first check self into system
        lock.acquire();
        // check if safe to read
        // if any writers, wait
        while (activeWriters + waitingWriters > 0) {
            ++waitingReaders;
            okToRead.wait(lock);
            --waitingReaders;
        }

        ++activeReaders;
        lock.release();

        // do something like reading from database

        // check self out of system
        lock.acquire();
        --activeReaders;
        // if no active readers and have waiting writers
        // wake up one writer
        if (activeReaders == 0 && waitingWriters > 0) {
            okToWrite.notifyOne(lock); // can only have one writer at a time
        }
        lock.release();
    }

    public void write() {
        lock.acquire();
        while (activeReaders + waitingReaders > 0) {
            ++waitingWriters;
            okToWrite.wait(lock);
            --waitingWriters;
        }
        ++activeWriters;
        lock.release();

        // do something like writing to database

        lock.acquire();
        --activeWriters;
        if (waitingWriters > 0) {
            okToWrite.notifyOne(lock); // can only have one writer at a time
        } else if (waitingReaders > 0) {
            okToRead.notifyAll(lock); // can have multiple readers
        }
        lock.release();
    }

    private int activeReaders;
    private int activeWriters;
    private int waitingReaders;
    private int waitingWriters;
    private Condition okToRead;
    private Condition okToWrite;
    private Lock lock;
}
