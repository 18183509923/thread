/**
 * 商品的服务的接口
 */
public interface GoodsService {
    //获取商品的信息
    public GoodsInfo getNum();
    //设置商品的数量
    public void setNum(int number);
}
