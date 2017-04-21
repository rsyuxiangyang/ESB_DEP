package org.fbi.endpoint.bestpay.com.bestpay.txn.base;

/**
 * Created by XIANGYANG on 2015-10-21.
 */
public class FasEbkAccount {
    public String accountCode;
    public String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
