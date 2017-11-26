package com.iloosen.imall.module.order.vo;

public class WsOrderItemVo {

    public WsOrderItemVo()
    {
    }

    public int getOrderItemId()
    {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId)
    {
        this.orderItemId = orderItemId;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public Double getProductUnitPrice()
    {
        return productUnitPrice;
    }

    public void setProductUnitPrice(Double productUnitPrice)
    {
        this.productUnitPrice = productUnitPrice;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public String toString()
    {
        return (new StringBuilder()).append("WsOrderItemVo{orderItemId=").append(orderItemId).append(", orderId=").append(orderId).append(", productId=").append(productId).append(", productUnitPrice=").append(productUnitPrice).append(", num=").append(num).append('}').toString();
    }

    private int orderItemId;
    private int orderId;
    private int productId;
    private Double productUnitPrice;
    private int num;
}
