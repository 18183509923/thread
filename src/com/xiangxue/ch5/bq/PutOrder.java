import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 将订单放入队列
 */
public class PutOrder implements Runnable {

    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        super();
        this.queue = queue;
    }
    @Override
    public void run() {
        //5秒到期
        Order ordeTb = new Order("Tb12345", (double) 366);
        ItemVo<Order> itemTb = new ItemVo<Order>(5000,ordeTb);
        queue.offer(itemTb);
        System.out.println("订单5秒后到期"+ordeTb.getOrderNo());

        //5秒到期
        Order ordeJd = new Order("orderJd", (double) 366);
        ItemVo<Order> itemJd = new ItemVo<Order>(8000,ordeJd);
        queue.offer(itemJd);
        System.out.println("订单8秒后到期"+ordeJd.getOrderNo());
    }
}
