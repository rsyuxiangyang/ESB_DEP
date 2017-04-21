package org.fbi.endpoint.bestpay.com.bestpay.txn.domain;

import org.fbi.endpoint.bestpay.com.bestpay.txn.base.FasBankAccount;

/**
 * Created by zhanrui on 2016-03-16.
 * Ç©Ô¼½Ó¿Ú
 */

public class T1409001Tia {
    public String reqIp;
    public String platCode;
    public String reqSeq;
    public String reqTime;
    public String custCode;
    public String extOrderSeq;
    public String currencyCode;
    public String validateType;
    public FasBankAccount bankAccount;
    public String netWorkNature;
    public String userFullName;
    public String ebkType;
    public String payeeName;
    public String netWorkAreaCode;
    public String arpType;

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

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public FasBankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(FasBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getNetWorkNature() {
        return netWorkNature;
    }

    public void setNetWorkNature(String netWorkNature) {
        this.netWorkNature = netWorkNature;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getEbkType() {
        return ebkType;
    }

    public void setEbkType(String ebkType) {
        this.ebkType = ebkType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getNetWorkAreaCode() {
        return netWorkAreaCode;
    }

    public void setNetWorkAreaCode(String netWorkAreaCode) {
        this.netWorkAreaCode = netWorkAreaCode;
    }

    public String getArpType() {
        return arpType;
    }

    public void setArpType(String arpType) {
        this.arpType = arpType;
    }
}
