import javax.naming.ServiceUnavailableException;

/**
 * notify/notifyAll
 * 快递实体类
 */
public class Express {
    //定义当前位置
    public final static String CITY = "上海";
    //快递运输里程数
    private int km;
    //快递到达地点
    private String site;

    public Express(){

    }

    public Express(int km, String site){
        this.km = km;
        this.site = site;
    }

    //变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
    public synchronized void changKm(){
        this.km = 101;
        notifyAll();
    }

    //变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理
    public synchronized void changSite(){
        this.site = "北京";
        notifyAll();
    }

    //判断公里数
    public synchronized void waitKm(){
        while (this.km<=100){
            try {
                wait();
                System.out.println("公里数线程["+Thread.currentThread().getId()+"] 是notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("这公里数"+this.km+"讲写入数据库");
    }

    //判断城市变化
    public synchronized void waitSite(){
        while (CITY.equals(this.site)){
            try {
                wait();
                System.out.println("城市线程["+Thread.currentThread().getId()+"]是notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("这城市是"+this.site+"将通知user");
    }
}
