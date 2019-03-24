import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 演示带版本戳的原子戳操作
 */
public class UseAto8micStampedReference {
    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("yan",0);

    public static void main(String[] args) throws InterruptedException {
        //拿初始的版本号
      final int oldStamp = asr.getStamp();
      //拿初始的名字
        final String oldReferenc = asr.getReference();

        System.out.println(oldReferenc+"===="+oldStamp);

        Thread rightStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"当前变量值："+oldReferenc+"当前版本戳："+oldStamp
                        +"==="+asr.compareAndSet(oldReferenc,oldReferenc+"java",
                        oldStamp,oldStamp+1));
            }
        });

        Thread errorStampThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String reference = asr.getReference();
                System.out.println(Thread.currentThread().getName()+"当前变量值："+reference+"当前版本戳："+asr.getStamp()
                        +"==="+asr.compareAndSet(reference, reference+"C",oldStamp,oldStamp+1));
            }
        });

        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference()+"======="+asr.getStamp());
    }
}
