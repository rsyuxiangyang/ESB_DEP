package org.fbi.endpoint.sbs.model.form.re;

import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.math.BigDecimal;

/**
 * ֪ͨ���-���֪ͨ9009504
 * (��ӦSBS-a111����)
 * ��������������֪ͨ�����ȡ����ǰ�����֪ͨ���ͻ�����֪ͨʱӦ��д֪ͨ��
 */
public class T220 extends SOFFormBody {

    {
        fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1};
        fieldLengths = new int[]{4, 4, 8, 2, 22, 8, 72, 3, 15, 16, 8};
    }

    private String TXNCDE; // ���״���
    private String TELLER; // ��Ա����
    private String TXNDAT; // �������ڣ��̶�����yyyyMMdd��
    private String ACTTY;  // �˻����
    private String IPTAC;  // �ʺ�
    private String ADVDAT; // ֪ͨ���ڣ��̶�����yyyyMMdd��
    private String ACTNAM; // ����
    private String INTCUR; // �ұ�
    private BigDecimal TXNAMT; // ֪ͨ���
    private String ADVNUM; // ֪ͨ����
    private String REMARK; // ��ע

    public String getTXNCDE() {
        return TXNCDE;
    }

    public void setTXNCDE(String TXNCDE) {
        this.TXNCDE = TXNCDE;
    }

    public String getTELLER() {
        return TELLER;
    }

    public void setTELLER(String TELLER) {
        this.TELLER = TELLER;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getACTTY() {
        return ACTTY;
    }

    public void setACTTY(String ACTTY) {
        this.ACTTY = ACTTY;
    }

    public String getIPTAC() {
        return IPTAC;
    }

    public void setIPTAC(String IPTAC) {
        this.IPTAC = IPTAC;
    }

    public String getADVDAT() {
        return ADVDAT;
    }

    public void setADVDAT(String ADVDAT) {
        this.ADVDAT = ADVDAT;
    }

    public String getACTNAM() {
        return ACTNAM;
    }

    public void setACTNAM(String ACTNAM) {
        this.ACTNAM = ACTNAM;
    }

    public String getINTCUR() {
        return INTCUR;
    }

    public void setINTCUR(String INTCUR) {
        this.INTCUR = INTCUR;
    }

    public BigDecimal getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(BigDecimal TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getADVNUM() {
        return ADVNUM;
    }

    public void setADVNUM(String ADVNUM) {
        this.ADVNUM = ADVNUM;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
