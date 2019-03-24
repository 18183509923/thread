import java.awt.*;

/**
 * join方法的使用
 */
public class UseJoin {
    static class JumpQueue implements Runnable {

        //用来插队的线程
        private Thread thread;

        public JumpQueue(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                System.out.println(thread.getName()+"将要插队之前"+Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"terminted....");
        }
    }

    public static void main(String[] args) {
        //现在是主线程
        Thread previous = Thread.currentThread();
        for (int i =0; i < 10; i++){
            //i=0, previous是主线程，i=1，previous是i=0这个线程
            Thread thread = new Thread(new JumpQueue(previous),String.valueOf(i));
            System.out.println(previous.getName()+"jump a queue the thread"+ thread.getName());
            thread.start();
            previous = thread;
        }
        //让主线程休眠2秒
        SleepTools.second(2);
        System.out.println(Thread.currentThread().getName()+"terminate.");
    }
}
