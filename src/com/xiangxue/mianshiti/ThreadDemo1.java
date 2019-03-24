import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 子线程循环 10 次，接着主线程循环 100 次，接着又回到子线程循环 10 次，
 * 接着再回到主线程又循环 100 次，如此循环50次，试写出代码
 */
public class ThreadDemo1 {
  private static final int COUNT = 50;
  private static final Object lock = new Object();
  //控制当前执行线程的权力
    private static AtomicBoolean permit = new AtomicBoolean(true);

    public static void main(String[] args) {
        ExecutorService exes = Executors.newSingleThreadExecutor();
        exes.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++){
                    synchronized (lock) {
                        while (!permit.get()) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("子线程循环十次");
                        permit.set(false);
                        lock.notifyAll();
                    }
                }
                System.out.println("子线程结束");
            }
        });
        for (int i = 0; i < COUNT; i++) {
            synchronized (lock) {
                while (permit.get()) {
                    System.out.println("主线程等待");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("主线程循环一百次");
                permit.set(true);
                lock.notifyAll();
            }
        }
        System.out.println("主线程结束");
        exes.shutdown();
    }

}
