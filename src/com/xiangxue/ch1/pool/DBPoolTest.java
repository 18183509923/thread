import javafx.concurrent.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试数据库连接池
 */
public class DBPoolTest {
    //创建10个数据库池
    static DBPool pool = new DBPool(10);
    //控制器，控制main线程将会等待所有woker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        //每个线程的操作次数
        int count = 20;
        //计数器：统计可以拿到连接的线程
        AtomicInteger got = new AtomicInteger();
        //计数器：统计没有拿到连接的线程
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread =new Thread(new Worker(count, got, notGot),"worker_"+i);
            thread.start();
        }
        //main线程在此处等待
        end.await();
        System.out.println("总共尝试了"+(threadCount * count));
        System.out.println("拿到连接的次数"+got);
        System.out.println("没有拿到连接的次数"+notGot);
    }

    static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        public void run(){
            while (count > 0) {
                //从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                //分别统计连接获取的数量got和未获取到的数量notGot
                try {
                    Connection connection = pool.fetchConn(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+"等待超时");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
