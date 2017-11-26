package com.iloosen.imall.module.bigdata.vo;

/**
 * Created by cjs on 2017/3/3.
 */
public class WsProductVo {
    private String productId;
    private String medicinalTypeCode;
    private String takeMedicinePeriod;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMedicinalTypeCode() {
        return medicinalTypeCode;
    }

    public void setMedicinalTypeCode(String medicinalTypeCode) {
        this.medicinalTypeCode = medicinalTypeCode;
    }

    public String getTakeMedicinePeriod() {
        return takeMedicinePeriod;
    }

    public void setTakeMedicinePeriod(String takeMedicinePeriod) {
        this.takeMedicinePeriod = takeMedicinePeriod;
    }
}
