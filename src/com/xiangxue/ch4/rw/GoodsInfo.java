/**
 * 商品的实体类
 */
public class GoodsInfo {
    //商品名字
    private final String name;
    //总销售额
    private double totalMoney;
    //库存数
    private int storeNumber;

    public GoodsInfo(String name, double totalMoney, int storeNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void changeNumber(int sellNumber) {
        this.totalMoney += sellNumber*25;
        this.storeNumber -= sellNumber;
    }
}
