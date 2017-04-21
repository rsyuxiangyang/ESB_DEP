package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasSignResponse;


public class T1409001ToaData {
    public String code;
    public String msg;
    public FasSignResponse result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FasSignResponse getResult() {
        return result;
    }

    public void setResult(FasSignResponse result) {
        this.result = result;
    }
}
