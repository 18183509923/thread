/**
 * 测试wait/notify/notifyAll
 */

public class TestWait {
    //实例一个Express
    private static Express express = new Express(0,Express.CITY);

    //检查里程数变化的线程，不满足条件，线程一直等待
    private static class CheckKm extends Thread{
        @Override
        public void run(){
            express.waitKm();
        }
    }
    //检查地点变化的线程，不满足条件，线程一直等待
    private static class CkeckSite extends Thread{
        @Override
        public void run(){
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //启动三个线程
        for (int i = 0; i < 3; i++){
            new CkeckSite().start();
        }
        //里程数的变化
        for (int i =0; i < 3; i++){
            new CheckKm().start();
        }

        Thread.sleep(1000);
        //快递地点变化
        express.changKm();

    }

}
