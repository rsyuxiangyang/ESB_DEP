package org.fbi.endpoint.bestpay.com.bestpay.txn.base;

/**
 * Created by XIANGYANG on 2015-10-21.
 */
public class FasBankAccount {
    public String bankCode;
    public String cardType;
    public String accountCode;
    public String bankCardName;
    public String certNo;
    public String certType;
    public String areaCode;
    public String perEntFlag;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPerEntFlag() {
        return perEntFlag;
    }

    public void setPerEntFlag(String perEntFlag) {
        this.perEntFlag = perEntFlag;
    }
}
