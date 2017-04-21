package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasRdResponse;

/**
 * Created by XIANGYANG on 2015-10-21.
 */

public class T1401001ToaData{
    public String code;
    public String msg;
    public FasRdResponse result;

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

    public FasRdResponse getResult() {
        return result;
    }

    public void setResult(FasRdResponse result) {
        this.result = result;
    }
}
