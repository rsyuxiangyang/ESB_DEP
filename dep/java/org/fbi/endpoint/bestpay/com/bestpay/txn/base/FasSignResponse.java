package org.fbi.endpoint.bestpay.com.bestpay.txn.base;

/**
 * Created by zhanrui on 2016-03-16.
 * Ç©Ô¼½Ó¿Ú
 */
public class FasSignResponse {
    public String signId;
    public String pgwSeq;

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getPgwSeq() {
        return pgwSeq;
    }

    public void setPgwSeq(String pgwSeq) {
        this.pgwSeq = pgwSeq;
    }
}
