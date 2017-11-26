package com.iloosen.imall.module.bigdata.vo;

/**
 * Created by cjp on 2017/3/6.
 */
public class ResultVo {
    /**
     * 是否成功标志
     */
    boolean success;
    /**
     * 失败，提示的错误信息
     */
    String errorMsg;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
