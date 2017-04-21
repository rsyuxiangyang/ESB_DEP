package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasQueryListResponse;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class T1403001Toa {
    public String sign;
    public T1403001ToaData data;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T1403001ToaData getData() {
        return data;
    }

    public void setData(T1403001ToaData data) {
        this.data = data;
    }

}
