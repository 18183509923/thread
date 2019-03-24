/**
 * 守护线程
 */
public class DaemonThread {
    private static class UseThread extends Thread{
        @Override
        public void run(){
            try {
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"我是继承Thread");
                }
                System.out.println(Thread.currentThread().getName()+"中断标志位"+isInterrupted());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(".....finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        //设置守护线程为true，则不运行finally里面的内容
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(3);
        //useThread.interrupt();
    }
}
