package answer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有一个残缺AtomicInteger的类实现了线程安全的；
 * get方法和compareAndSet方法
 * 自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomic = new AtomicInteger(0);

    public void increament() {
        for (;;) {
            int i = atomic.get();
            boolean suc = atomic.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }
    public int getCount() {
        return atomic.get();
    }

}
