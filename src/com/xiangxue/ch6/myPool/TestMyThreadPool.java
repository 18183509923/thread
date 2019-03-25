import sun.awt.windows.ThemeReader;

import java.util.Random;
import java.util.concurrent.Executor;

/**
 * 测试线程池
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //创建3个线程得线程池
        MyThreadPool2 t = new MyThreadPool2(3,0);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));
        System.out.println(t);
        Thread.sleep(10000);
        //所有线程都执行完成才destory
        t.destroy();
        System.out.println(t);
    }

    //任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //执行任务
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+"睡眠 中断异常"+Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            System.out.println("任务 "+name+" 完成");
        }
    }

}
