import javax.jws.Oneway;

/**
 * 测试join
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoinTest t1 = new ThreadJoinTest("三");
        ThreadJoinTest t2 = new ThreadJoinTest("四");
        ThreadJoinTest t3 = new ThreadJoinTest("五");

        /**
         * join的意思是使得放弃当前线程的执行，并返回对应的线程
         * 程序在main线程中调用t1线程join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         * 所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        //t1.join(5);
        t1.join();
        t1.start();
        t2.start();

        //t1.join();
        //t3.start();
    }
}

    class ThreadJoinTest extends Thread{
        public ThreadJoinTest(String name){
            super(name);
        }
        @Override
        public void run(){
            for (int i = 0; i < 1000; i++) {
                System.out.println(this.getName()+":"+i);
            }
        }
}
