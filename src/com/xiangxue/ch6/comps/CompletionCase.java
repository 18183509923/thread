import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试CompletionService
 */
public class CompletionCase {
    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors()*3;

    //方法一，自己写集合来实现获取线程池中任务的返回结果
    public void testByQueue() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        //统计所有任务休眠的总时长
        AtomicInteger count = new AtomicInteger(0);
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        //容器存放提交给线程池的任务
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>();

        //向里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = pool.submit(new WorkTask("ExecTask"+i));
            //i=0 先进队列，i=1的任务跟着进
            queue.add(future);
        }

        //检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            //i=0 先取到，i=1的后取到
            int sleptTime = queue.take().get();
            System.out.println("slept"+sleptTime+"ms...");
        }

        //关闭线程池
        pool.shutdown();
        System.out.println("...........任务睡眠时间"+count.get()+"ms, 并花多长时间"+(System.currentTimeMillis()-start)+"ms");
    }


    //方法二，通过CompletionService来实现获取线程池中任务的返回结果
    public void testByCompService() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        //创建线程
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<Integer> cService = new ExecutorCompletionService<>(pool);

        //向里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            cService.submit(new WorkTask("ExecTask" + i));
        }

        //检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime = cService.take().get();
            System.out.println("slept"+sleptTime+" ms");
            count.addAndGet(sleptTime);
        }

        //关闭线程池
        pool.shutdown();
        System.out.println("..........任务睡眠时间"+count.get()+"ms, 和执行时间"+(System.currentTimeMillis()-start));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionCase c = new CompletionCase();
        c.testByQueue();
        c.testByCompService();
    }
}

