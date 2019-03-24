import java.util.concurrent.DelayQueue;

/**
 * 取出到期订单的功能
 */
public class FetchOrder implements Runnable{

    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
        super();
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                ItemVo<Order> item = queue.take();
                Order order = (Order)item.getDate();
                System.out.println("从队列中取出"+order.getOrderNo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
