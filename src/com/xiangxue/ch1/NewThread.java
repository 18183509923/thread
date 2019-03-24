import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 三种新启线程的方法
 */
public class NewThread {
//扩展自Thread类

    //实现Runnable接口
    private static class UseRun implements Runnable{
        @Override
        public void run() {
            System.out.println("我是实现Runnable");
        }
    }


    //实现Callable接口
    private static class UseCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("我是实现Callable");
            return "CallResult";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCall useCall = new UseCall();
        FutureTask<String> futureTask = new FutureTask<>(useCall);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
