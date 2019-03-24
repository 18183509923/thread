/**
 * 使用RecursiveTask
 */
public class SumNormal {
    public static void main(String[] args) {
        int count = 0;
        int[] src = forkjoin.sum.MarkArray.makeArray();

        long start = System.currentTimeMillis();
        for (int i =0; i < src.length; i++){
            //SleepTools.ms(1);
            count =  count +src[i];
        }
        System.out.println("这计数是 "+count+" 花费时间"+(System.currentTimeMillis()-start)+"ms");
    }
}
