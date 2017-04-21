package org.fbi.endpoint.sbs.model.form.re;

import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * ֪ͨ���-����ͬʱ�Զ������˺Ž���9009503
 * (��ӦSBS-a27a����)
 */
public class T104 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        fieldLengths = new int[]{7, 16, 8, 34, 72, 18, 8, 8, 3, 15, 2, 2, 9};
    }

    private String CUSIDT; // �ͻ���
    private String OURREF; // ժҪ
    private String TXNDAT; // ��������
    private String ORGNAM; // ������
    private String ACTNAM; // ����
    private String BOKNUM; // �˻���
    private String VALDAT; // ������
    private String EXPDAT; // ������
    private String INTCUR; // �ұ�
    private String TXNAMT; // ���
    private String DPTTYP; // �������
    private String DPTPRD; // ����
    private String INTRAT; // ����

    public String getCUSIDT() {
        return CUSIDT;
    }

    public void setCUSIDT(String CUSIDT) {
        this.CUSIDT = CUSIDT;
    }

    public String getOURREF() {
        return OURREF;
    }

    public void setOURREF(String OURREF) {
        this.OURREF = OURREF;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getORGNAM() {
        return ORGNAM;
    }

    public void setORGNAM(String ORGNAM) {
        this.ORGNAM = ORGNAM;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getBOKNUM() {
        return BOKNUM;
    }

    public void setBOKNUM(String BOKNUM) {
        this.BOKNUM = BOKNUM;
    }

    public String getVALDAT() {
        return VALDAT;
    }

    public void setVALDAT(String VALDAT) {
        this.VALDAT = VALDAT;
    }

    public String getEXPDAT() {
        return EXPDAT;
    }

    public void setEXPDAT(String EXPDAT) {
        this.EXPDAT = EXPDAT;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getDPTPRD() {
        return DPTPRD;
    }

    public void setDPTPRD(String DPTPRD) {
        this.DPTPRD = DPTPRD;
    }

    public String getINTRAT() {
        return INTRAT;
    }

    public void setINTRAT(String INTRAT) {
        this.INTRAT = INTRAT;
    }
}
