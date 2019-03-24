/**
 * start和run方法的区别
 */
public class StartAndRun {
    public static class ThreadRun extends Thread{
        @Override
        public void run(){
            int i = 100;
            while (i > 0){
                SleepTools.ms(1000);
                System.out.println("我是"+Thread.currentThread().getName()+i--);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun beCalled = new ThreadRun();
        beCalled.setName("子线程");
        //run方法不会拥有自己的线程
        beCalled.run();
        //调用start方法才能拥有自己的线程



        beCalled.setPriority(5);

        beCalled.start();
    }
}
