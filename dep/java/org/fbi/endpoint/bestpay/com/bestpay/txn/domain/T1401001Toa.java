package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class T1401001Toa{
    public String sign;
    public T1401001ToaData data;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T1401001ToaData getData() {
        return data;
    }

    public void setData(T1401001ToaData data) {
        this.data = data;
    }
}
