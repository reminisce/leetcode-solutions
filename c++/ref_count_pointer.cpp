/*
Implement a smart pointer based upon
reference counting.
*/

#ifndef REF_COUNT_POINTER
#define REF_COUNT_POINTER

template <typename T>
class RefCountPointer {
public:
    explicit RefCountPointer(T* p) : m_refCounter(nullptr) {
        if (p != nullptr) {
            m_refCounter = new RefCounter;
            m_refCounter->p = p;
            ++m_refCounter;
        }
    }

    ~RefCountPointer() { release(); }

    RefCountPointer(const RefCountPointer& rcp) { acquire(rcp.getRefCounter()); }

    RefCountPointer& operator=(const RefCountPointer& rcp) {
        if (this != &rcp) {
            release();
            acquire(rcp.getRefCounter());
        }
        return *this;
    }

    RefCounter* getRefCounter() { return m_refCounter; }

    T& operator*() const { return *(getRefCounter()->p); }
    T* operator->() const { return getRefCounter()->p; }

private:
    struct RefCounter {
        RefCounter() : p(nullptr), cnt(0) {}
        T* p;
        int cnt;
    };

    void release() {
        if (m_refCounter) {
            if (--m_refCounter->cnt == 0) {
                delete p;
                p = nullptr;
                delete m_refCounter;
                m_refCounter = nullptr;
            }
        }
    }

    void acquire(RefCounter* rcp) {
        m_refCounter = rcp;
        if (rcp) ++rcp->cnt;
    }

    RefCounter* m_refCounter;
};

#endif