import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewTread1 {
  private static class UseRun implements Runnable{

      @Override
      public void run() {
          System.out.println("Runnable");
      }
  }

    public static void main(String[] args) {
        UseRun useRun = new UseRun();
        new Thread(useRun).start();
        System.out.println("=======");

    }
}
