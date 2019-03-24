import java.util.concurrent.CountDownLatch;

/**
 *CountDownLatch方法，有5个初始化的线程，6个扣除点，
 * 扣除完毕以后，主线程和业务线程才能继续自己的工作
 */
public class UseCountDownLatch {
    //打印6个CountDownLatch点
    static CountDownLatch latch = new CountDownLatch(6);

    //初始化线程（只有一步，有4个）
    private static class InitThread implements Runnable{

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getId()+"准备初始化工作");
            //初始化线程完成工作了，countDown方法只扣减一次
            latch.countDown();
            for (int i = 0; i < 2; i++){
                System.out.println("线程"+Thread.currentThread().getId()+"继续他的工作");
            }
        }
    }

    //业务线程
    private static class BusiThread implements Runnable{

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++){
                System.out.println("BusiThread"+Thread.currentThread().getId()+"做业务");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //单独的初始化线程，初始化分为2步，需要扣减两次
        new Thread(new Runnable() {
            @Override
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread"+Thread.currentThread().getId()+"准备初始化工作步骤1");
                //每完成一步初始化工作就扣减一次
                latch.countDown();
                System.out.println("开始第二步");
                SleepTools.ms(1);
                System.out.println("Thread"+Thread.currentThread().getId()+"准备初始化工作步骤2");
                //每完成一步初始化工作就扣减一次
                latch.countDown();
            }
        }).start();
        new Thread(new BusiThread()).start();
        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.await();
        System.out.println("主线程做自己的工作");
    }
}
