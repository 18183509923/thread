/**
 * 订单的实体类
 */
public class Order {
    //订单的编号
    private final String orderNo;
    //订单的金额
    private final Double orderMoney;

    public Order(String orderNo, Double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }
}
