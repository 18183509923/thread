import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现一个自己的类ReentrantLock锁
 */
public class SelfLock implements Lock{

    //state 表示获取到锁，state=1，获取到了锁，state=0，表示这个锁当前没有线程拿到
    private static class Sync extends AbstractQueuedSynchronizer{

        //是否占用
        protected boolean isHelExclusively() {
            return getState() == 1;
        }

        //尝试独占式获取
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //独占式释放
        protected boolean tryRelease(int arg) {
            if (getState()==0) {
                throw new UnsupportedOperationException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
