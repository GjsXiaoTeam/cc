package hbase.resultVo;

/**
 * Created by SEELE on 2017/5/6.
 */
public class OrderVo {
    private String reportTime;      //时间维度
    private float orderPriceTotal;  //下单金额
    private int orderProductCount;  //下单商品件数
    private int orderCount;         //下单数量
    private int orderUserCount;     //下单客户数
    private float orderSalePrice;   //客单价

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public float getOrderPriceTotal() {
        return orderPriceTotal;
    }

    public void setOrderPriceTotal(float orderPriceTotal) {
        this.orderPriceTotal = orderPriceTotal;
    }

    public int getOrderProductCount() {
        return orderProductCount;
    }

    public void setOrderProductCount(int orderProductCount) {
        this.orderProductCount = orderProductCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getOrderUserCount() {
        return orderUserCount;
    }

    public void setOrderUserCount(int orderUserCount) {
        this.orderUserCount = orderUserCount;
    }

    public float getOrderSalePrice() {
        return orderSalePrice;
    }

    public void setOrderSalePrice(float orderSalePrice) {
        this.orderSalePrice = orderSalePrice;
    }
}
