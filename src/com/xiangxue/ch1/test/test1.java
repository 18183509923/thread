package test;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding;
import javax.naming.Name;
import java.lang.management.ThreadInfo;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class test1 {
   private static class UseThread extends Thread{
       public UseThread(String name){
           super(name);
       }
       @Override
       public void run(){
           String threadName = Thread.currentThread().getName();
           while (!isInterrupted()){
               System.out.println(threadName+"在运行");
           }
           System.out.println(threadName+"中断标志位"+isInterrupted());
       }
   }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(10);
        endThread.interrupt();
    }
}
