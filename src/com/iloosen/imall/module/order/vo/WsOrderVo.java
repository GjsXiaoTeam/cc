package com.iloosen.imall.module.order.vo;

public class WsOrderVo {
	 public WsOrderVo()
	    {
	    }

	    public int getOrderId()
	    {
	        return orderId;
	    }

	    public void setOrderId(int orderId)
	    {
	        this.orderId = orderId;
	    }

	    public int getUserId()
	    {
	        return userId;
	    }

	    public void setUserId(int userId)
	    {
	        this.userId = userId;
	    }

	    public String getOrderNum()
	    {
	        return orderNum;
	    }

	    public void setOrderNum(String orderNum)
	    {
	        this.orderNum = orderNum;
	    }

	    public Double getPayment()
	    {
	        return payment;
	    }

	    public void setPayment(Double payment)
	    {
	        this.payment = payment;
	    }

	    public Double getProductTotalAmount()
	    {
	        return productTotalAmount;
	    }

	    public void setProductTotalAmount(Double productTotalAmount)
	    {
	        this.productTotalAmount = productTotalAmount;
	    }

	    public String getIsCod()
	    {
	        return isCod;
	    }

	    public void setIsCod(String isCod)
	    {
	        this.isCod = isCod;
	    }

	    public String getOrderStatus()
	    {
	        return orderStatus;
	    }

	    public void setOrderStatus(String orderStatus)
	    {
	        this.orderStatus = orderStatus;
	    }

	    public String toString()
	    {
	        return (new StringBuilder()).append("WsOrderVo{orderId=").append(orderId).append(", userId=").append(userId).append(", orderNum='").append(orderNum).append('\'').append(", payment=").append(payment).append(", productTotalAmount=").append(productTotalAmount).append(", isCod='").append(isCod).append('\'').append(", orderStatus='").append(orderStatus).append('\'').append('}').toString();
	    }

	    private int orderId;
	    private int userId;
	    private String orderNum;
	    private Double payment;
	    private Double productTotalAmount;
	    private String isCod;
	    private String orderStatus;
}
