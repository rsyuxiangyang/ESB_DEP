package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;
import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasEbkAccount;

/**
 * Created by XIANGYANG on 2015-10-21.
 * 翼支付 代扣
 */
public class T1401001Tia {
    public String reqIp;
    public String platCode;
    public String reqSeq;
    public String reqTime;
    public String custCode;
    public String extOrderSeq;
    public String currencyCode;
    public String amount;
    public String signId;
    public String accountCode;
    public String customerTransCode;   //外部业务编码 可选
    public FasEbkAccount payeeAccount;

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }

    public String getReqSeq() {
        return reqSeq;
    }

    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getExtOrderSeq() {
        return extOrderSeq;
    }

    public void setExtOrderSeq(String extOrderSeq) {
        this.extOrderSeq = extOrderSeq;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCustomerTransCode() {
        return customerTransCode;
    }

    public void setCustomerTransCode(String customerTransCode) {
        this.customerTransCode = customerTransCode;
    }

    public FasEbkAccount getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(FasEbkAccount payeeAccount) {
        this.payeeAccount = payeeAccount;
    }
}
