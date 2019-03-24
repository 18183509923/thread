import java.text.SimpleDateFormat;

/**
 * 抛出InterruptedExcetion异常的时候，要注意中断标志位，需要自己重新手动设置中断标志位
 */
public class HasInterruptException {

    private static SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static class UseThread extends Thread{
        public UseThread(String name){
            super(name);
        }

        @Override
        public void run(){
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(threadName+"中断标志位"+isInterrupted());
                    //这里需要再次interrupt，才能把中断标志位设为true，否则为false
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName);
            }
            System.out.println(threadName+"中断标志位"+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("Interrupt异常");
        endThread.start();
        Thread.sleep(100);
        endThread.interrupt();
    }
}
