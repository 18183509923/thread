/**
 * 中断Runnable接口的线程
 */
public class EndRunnable {
    private static class UseRunnable implements Runnable{
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(threadName+"在运行");
            }
            System.out.println(threadName+"中断线程"+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(10);
        endThread.interrupt();
    }
}
