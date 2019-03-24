/**
 * Seelp休眠锁的释放
 */
public class SleepLock {
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadNotSleep();
        threadA.setName("A线程休眠 ");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("B线程休眠 ");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.out.println("主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

    private class ThreadSleep extends Thread{
        @Override
        public void run(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"会拿走锁 ");
            try {
                synchronized (lock) {
                    System.out.println(threadName+"持锁 ");
                    Thread.sleep(5000);
                    System.out.println("完成这项工作 "+threadName);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class ThreadNotSleep extends Thread{

        public void run(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"会拿走锁的时间 "+System.currentTimeMillis());
            synchronized (lock) {
                System.out.println(threadName+"持锁的时间 "+System.currentTimeMillis());
                System.out.println("完成这项工作 "+threadName);
            }
        }
    }
}
