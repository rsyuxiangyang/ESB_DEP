package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasEbkAccount;

/**
 * Created by XIANGYANG on 2015-10-21.
 */
public class T1403001Tia {
    public String reqSeq;
    public String custCode;
    public String platCode;
    public String reqIp;
    public String originalSeq;

    public String getReqSeq() {
        return reqSeq;
    }

    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getOriginalSeq() {
        return originalSeq;
    }

    public void setOriginalSeq(String originalSeq) {
        this.originalSeq = originalSeq;
    }
}
