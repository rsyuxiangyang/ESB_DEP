package org.fbi.endpoint.sbs.model.form.re;

import org.fbi.endpoint.sbs.domain.SOFFormBody;

/**
 *  ��ӦSBS-n022����
 */
public class T543 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1};
        fieldLengths = new int[]{4, 4, 2, 32, 18};
    }

    private String TLRNUM;	// ��Ա��
    private String VCHSET;	// ��Ʊ�׺�
    private String SETSEQ;	// ��Ʊ����˳���
    private String TXNSEQ;   // ������ˮ��
    private String FBTIDX;	// ��Χϵͳ��ˮ��

    public String getTLRNUM() {
        return TLRNUM;
    }

    public void setTLRNUM(String TLRNUM) {
        this.TLRNUM = TLRNUM;
    }

    public String getVCHSET() {
        return VCHSET;
    }

    public void setVCHSET(String VCHSET) {
        this.VCHSET = VCHSET;
    }

    public String getSETSEQ() {
        return SETSEQ;
    }

    public void setSETSEQ(String SETSEQ) {
        this.SETSEQ = SETSEQ;
    }

    public String getTXNSEQ() {
        return TXNSEQ;
    }

    public void setTXNSEQ(String TXNSEQ) {
        this.TXNSEQ = TXNSEQ;
    }

    public String getFBTIDX() {
        return FBTIDX;
    }

    public void setFBTIDX(String FBTIDX) {
        this.FBTIDX = FBTIDX;
    }
}
