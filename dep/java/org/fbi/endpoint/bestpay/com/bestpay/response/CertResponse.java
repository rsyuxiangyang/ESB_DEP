package org.fbi.endpoint.bestpay.com.bestpay.response;

import java.io.Serializable;
import java.util.Map;

/**
 * 翼支付前置调用返回的加签对象
 */
public class CertResponse implements Serializable {
    private String sign;
    private Map data;

    public Map getData() {
        return this.data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}