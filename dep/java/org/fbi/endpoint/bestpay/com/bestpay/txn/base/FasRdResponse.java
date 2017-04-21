package org.fbi.endpoint.bestpay.com.bestpay.txn.base;

/**
 * Created by XIANGYANG on 2015-10-21.
 */
public class FasRdResponse {
    public String result;
    public String reserverDomain1;
    public String reserverDomain2;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReserverDomain1() {
        return reserverDomain1;
    }

    public void setReserverDomain1(String reserverDomain1) {
        this.reserverDomain1 = reserverDomain1;
    }

    public String getReserverDomain2() {
        return reserverDomain2;
    }

    public void setReserverDomain2(String reserverDomain2) {
        this.reserverDomain2 = reserverDomain2;
    }
}
