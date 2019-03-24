package syn;

/**
 * synchronized内置锁
 */
public class SynTest {
    //初始100000
    private int age = 100000;

    public void setAge(){
        age = age+20;
    }

    private static class TestThread extends Thread{
        private SynTest synTest;

        public TestThread(SynTest synTest, String name){
            super(name);
            this.synTest = synTest;
        }

        @Override
        public void run(){
            //递增10000
            for (int i = 0; i < 100000; i++){

            }
            System.out.println(Thread.currentThread().getName()+"  age = "+synTest.getAge());
        }
    }
    public synchronized void test(){
        age++;
        test2();
    }

    public synchronized void test2(){
        {
            age--;
        }
    }
    public int getAge(){
        return age;
    }

    public static void main(String[] args) {
        SynTest synTest = new SynTest();
        Thread endThread = new TestThread(synTest,"endThread");
        endThread.start();
        //递减10000
        for(int i =0; i < 100000; i++){
            synTest.test2();
        }
        System.out.println(Thread.currentThread().getName()+"  age = "+synTest.getAge());
    }
}
