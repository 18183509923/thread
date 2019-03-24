import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用Lock实现接口的方法
 */
public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;
    //创建一个读写锁
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    //读锁
    private final Lock getLock = lock.readLock();
    //写锁
    private final Lock setLock = lock.writeLock();
    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        }finally {
            getLock.unlock();
        }

    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        }finally {
            setLock.unlock();
        }
    }
}
