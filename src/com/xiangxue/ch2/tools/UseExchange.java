import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchange的使用
 */
public class UseExchange {

    private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();

    public static void main(String[] args) {
        //第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setA = new HashSet<String>();
                //添加数据
                //set.add(...)
                //交换set
                try {
                    setA = exchange.exchange(setA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //存放数据的容器
                Set<String> setB = new HashSet<String>();
                //添加数据
                //set.add(...)
                try {
                    setB = exchange.exchange(setB);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
