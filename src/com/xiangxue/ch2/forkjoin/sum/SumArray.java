import javax.jws.Oneway;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 测试RecursiveTask,有返回值
 */
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{
        private final static int THRASHOLD = forkjoin.sum.MarkArray.ARRAY_LENGTH/10;
        //表示我们要实际统计的数组
        private int[] src;
        //开始统计的下标
        private int fromIndex;
        //统计到哪里结束的下标
        private int toINdex;

        public SumTask(int[] src, int fromIndex, int toINdex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toINdex = toINdex;
        }
        @Override
        protected Integer compute(){
            if (toINdex-fromIndex < THRASHOLD) {
                int count = 0;
                for (int i = fromIndex; i < toINdex; i++) {
                    //SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            }else {
                int mid = (fromIndex+toINdex)/2;
                SumTask left = new SumTask(src,fromIndex,mid);
                SumTask right = new SumTask(src,mid+1,toINdex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) {
        //创建一个pool
        ForkJoinPool pool = new ForkJoinPool();
        //定义一个数组
        int[] src = forkjoin.sum.MarkArray.makeArray();

        SumTask innerFind = new SumTask(src,0,src.length-1);
        //系统时间
        long start = System.currentTimeMillis();

        //同步调用
        pool.invoke(innerFind);
        System.out.println("是Task运行");

        System.out.println("计数是"+innerFind.join()+" 花费时间："+(System.currentTimeMillis()-start+"ms"));
    }
}
